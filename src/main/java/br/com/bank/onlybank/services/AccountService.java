package br.com.bank.onlybank.services;

import br.com.bank.onlybank.dtos.AccountDTO;
import br.com.bank.onlybank.dtos.DepositDTO;
import br.com.bank.onlybank.dtos.RemoveMoneyDTO;
import br.com.bank.onlybank.dtos.NewAccountDTO;
import br.com.bank.onlybank.dtos.TransferDTO;
import br.com.bank.onlybank.dtos.UpdateAccountDTO;
import br.com.bank.onlybank.entities.Account;
import br.com.bank.onlybank.entities.Agency;
import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.entities.Extract;
import br.com.bank.onlybank.enums.AccountType;
import br.com.bank.onlybank.enums.OperationType;
import br.com.bank.onlybank.enums.Profile;
import br.com.bank.onlybank.exceptions.OnlyBankException;
import br.com.bank.onlybank.repositories.AccountRepository;
import br.com.bank.onlybank.services.impl.GenericServiceImpl;
import br.com.bank.onlybank.utils.AccountExtract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService extends GenericServiceImpl<Account, Long> {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AgencyService agencyService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private BankService bankService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ExtractService extractService;

    @Autowired
    private UserService userService;

    public AccountService(AccountRepository accountRepository) {
        super(accountRepository);
        this.accountRepository = accountRepository;
    }

    @Override
    public List<Account> listAll() {
        return findAll().get();
    }

    public Optional<List<Account>> findAll() {
        if (userService.hasRole(Profile.ADMIN)) {
            return Optional.of(super.listAll());
        }

        return accountRepository.findByClient(userService.getUserDetails().getClient());
    }

    @Override
    public Page<Account> sort(Integer page, Integer linesPerPage, String orderBy, String direction) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);

        if (userService.hasRole(Profile.ADMIN)) {
            return accountRepository.findAll(pageRequest);
        }

        return accountRepository.findByClient(userService.getUserDetails().getClient(), pageRequest);
    }

    @Override
    public Account find(Long key) {
        Optional<Account> conta = accountRepository.findById(key);

        userService.validateClientAccount(key);

        return conta.orElseThrow(() -> new OnlyBankException(
                "Object not found! Id: " + key + ", Type: " + Account.class.getName()));
    }

    @Override
    public Account update(Account newAccount) {
        Account account = find(newAccount.getId());
        newAccount = update(account, newAccount);
        return accountRepository.save(newAccount);
    }

    @Override
    protected Account update(Account entity, Account newEntity) {
        Account account = new Account(newEntity.getNumber(), newEntity.getType(), newEntity.getBalance());
        account.setId(entity.getId());
        account.setClient(entity.getClient());
        account.setAgency(entity.getAgency());

        return account;
    }

    @Override
    public void delete(Long key) {
        find(key);

        try {
            accountRepository.deleteById(key);
        } catch (OnlyBankException e) {
            throw new OnlyBankException("Não é possível excluir a conta que possuí contas!");
        }
    }

    public Account convertsDtoToEntity(AccountDTO accountDTO) {
        return new Account(accountDTO.getNumber(), AccountType.valueOf(accountDTO.getType()),
                accountDTO.getBalance());
    }

    public Account convertsDtoToEntity(UpdateAccountDTO updateAccountDTO) {
        return new Account(updateAccountDTO.getNumber(), AccountType.valueOf(updateAccountDTO.getType()),
                updateAccountDTO.getBalance());
    }

    public Account convertsDtoToEntity(NewAccountDTO newAccountDTO) {
        Account account = new Account(newAccountDTO.getNumber(), AccountType.valueOf(newAccountDTO.getType()),
                newAccountDTO.getBalance());

        Client client = new Client(newAccountDTO.getName(), newAccountDTO.getCpf(), bCryptPasswordEncoder.encode(newAccountDTO.getKey()));
        Agency agency = agencyService.findByAgency(newAccountDTO.getBankId(), newAccountDTO.getAgencyNumber());

        account.setClient(client);
        account.setAgency(agency);

        return account;
    }

    public Account find(String number) {
        Optional<Account> account = accountRepository.find(number);

        return account.orElseThrow(() -> new OnlyBankException("Account not found! number: " + number));
    }

    public Account findNumberAgency(String accountNumber, String agencyNumber, Long bankId) {
        Optional<Account> account = accountRepository.findNumberAgency(accountNumber, agencyNumber,
                bankId);

        return account.orElseThrow(() -> new OnlyBankException(
                "Account not found! number: " + accountNumber + ", in agency number: " + agencyNumber));
    }

    public Account insert(Account account) {

        try {
            validateAccount(account.getNumber(), account.getAgency().getNumber(), account.getAgency().getBank().getId());
        } catch (OnlyBankException e) {

            Client client = clientService.save(account.getClient());
            Account newAccount = new Account(account.getNumber(), account.getType(), client, account.getAgency(),
                    account.getBalance());

            return save(newAccount);
        }

        throw new OnlyBankException("There is already an account registered at the agency with the number provided!");
    }

    public Account validateAccount(String accountNumber, String agencyNumber, Long bankId) {
        bankService.find(bankId);

        agencyService.findByAgency(bankId, agencyNumber);

        Account account = findNumberAgency(accountNumber, agencyNumber, bankId);

        return account;
    }

    public AccountExtract removeMoney(RemoveMoneyDTO removeMoneyDTO) {
        Account account = validateAccount(removeMoneyDTO.getAccountNumber(), removeMoneyDTO.getAgencyNumber(), removeMoneyDTO.getBankId());

        userService.validateClientAccount(account);

        Extract extract = debit(account, removeMoneyDTO.getValue());

        return new AccountExtract(account.getId(), extract.getId());
    }



    public AccountExtract deposit(DepositDTO depositDTO) {
        Account account = validateAccount(depositDTO.getAccountNumber(), depositDTO.getAgencyNumber(), depositDTO.getBankId());

        Extract extract = credit(account, depositDTO.getValue());

        return new AccountExtract(account.getId(), extract.getId());
    }

    public AccountExtract transfer(TransferDTO transferDTO) {
        Account accountOrigin = validateAccount(transferDTO.getAccountOriginNumber(), transferDTO.getAgencyOriginNumber(), transferDTO.getBankOriginId());

        userService.validateClientAccount(accountOrigin);

        Account accountDestiny = validateAccount(transferDTO.getAccountDestinyNumber(),
                transferDTO.getAgencyDestinyNumber(), transferDTO.getBankDestinyId());

        AccountExtract extract = transfer(accountOrigin, accountDestiny, transferDTO.getValue());

        return extract;
    }

    private Extract debit(Account account, Double value) {
        return debit(account, value, null, null);
    }

    private Extract debit(Account account, Double value, OperationType type, Account accountDestiny) {
        if (value <= account.getBalance()) {
            account.setBalance(account.getBalance() - value);
            account = accountRepository.save(account);

            Extract extractDebit = extractService.generate(false, account, type == null ? OperationType.REMOVAL : type, value, accountDestiny);

            return extractDebit;
        }

        throw new OnlyBankException(
                String.format("The account does not have sufficient balance for this operation! Balance: {0}, amount to be debited {1}",
                        account.getBalance(), value));
    }

    private Extract credit(Account account, Double value) {
        return credit(account, value, null, null);
    }

    private Extract credit(Account account, Double value, OperationType type, Account accountOrigin) {
        account.setBalance(account.getBalance() + value);
        account = accountRepository.save(account);

        Extract extractCredit = extractService.generate(true, account, type == null ? OperationType.DEPOSIT : type, value, accountOrigin);

        return extractCredit;
    }

    private AccountExtract transfer(Account accountOrigin, Account accountDestiny, Double value) {
        Extract extractDebit = debit(accountOrigin, value, OperationType.TRANSFER, accountDestiny);
        credit(accountDestiny, value, OperationType.TRANSFER, accountOrigin);

        return new AccountExtract(accountOrigin.getId(), extractDebit.getId());
    }
}

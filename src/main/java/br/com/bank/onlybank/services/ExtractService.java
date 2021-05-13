package br.com.bank.onlybank.services;

import br.com.bank.onlybank.entities.Account;
import br.com.bank.onlybank.entities.Extract;
import br.com.bank.onlybank.enums.OperationType;
import br.com.bank.onlybank.repositories.ExtractRepository;
import br.com.bank.onlybank.services.impl.GenericServiceImpl;
import br.com.bank.onlybank.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
public class ExtractService extends GenericServiceImpl<Extract, Long> {

    @Autowired
    private ExtractRepository extractRepository;

    @Autowired
    private DataUtil dataUtil;

    @Autowired
    private UserService userService;

    public ExtractService(ExtractRepository extractRepository) {
        super(extractRepository);
        this.extractRepository = extractRepository;
    }

    @Override
    protected Extract update(Extract entity, Extract newEntity) {
        return null;
    }

    public Extract generate(Boolean credit, Account account, OperationType type, Double value) {
        return generate(credit, account, type, value, null);
    }

    public Extract generate(Boolean credit, Account account, OperationType type, Double value, Account accountDestiny) {
        Calendar data = Calendar.getInstance();

        Extract extract = new Extract(data, type, value, account);
        extract.setInformations(accountInformation(credit, account, type, value, accountDestiny, data));
        extract = extractRepository.save(extract);

        return extract;
    }

    private String accountInformation(Boolean credit, Account account, OperationType type, Double value, Account accountDestiny, Calendar data) {
        if(credit) {
            return creditInformation(accountDestiny, type, value, account, data);
        }else{
            return debitInformation(account, type, value, accountDestiny, data);
        }
    }

    private String debitInformation(Account account, OperationType type, Double value, Account accountDestiny, Calendar data) {
        if(OperationType.TRANSFER.equals(type)) {
            return String.format("DATA: %s\n"
                            + "TRANSFER PERFORMEDR$ %.2f\n"
                            + "PARA %s, ACCOUNT: %s AG: %s", dataUtil.dateFormat(data.getTime()), value, account.getClient().getName().split(" ")[0],
                    account.getNumber(), account.getAgency().getNumber());
        }else {
            return String.format("DATA: %s\n"
                    + "REMOVAL R$ %.2f", dataUtil.dateFormat(data.getTime()), value);
        }
    }

    private String creditInformation(Account account, OperationType type, Double value, Account accountDestiny, Calendar data) {
        if(OperationType.TRANSFER.equals(type)) {
            return String.format("DATA: %s\n"
                            + "TRANFER RECEIVED R$ %.2f\n"
                            + "POR %s, ACCOUNT: %s AG: %s", dataUtil.dateFormat(data.getTime()), value, accountDestiny.getClient().getName().split(" ")[0],
                    accountDestiny.getNumber(), accountDestiny.getAgency().getNumber());
        }else {
            return String.format("DATA: %s\n"
                    + "DEPOSIT OF R$ %.2f", dataUtil.dateFormat(data.getTime()), value);
        }
    }

    public List<Extract> listAllByAccount(Long id) {
        userService.validateClientAccount(id);
        return extractRepository. listallbyaccount(id);
    }

    public Extract find(Long id, Long extractId) {
        userService.validateClientAccount(id);
        return find(extractId);
    }
}

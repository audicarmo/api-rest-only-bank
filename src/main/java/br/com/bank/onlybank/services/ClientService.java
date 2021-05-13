package br.com.bank.onlybank.services;

import br.com.bank.onlybank.dtos.NewClientDTO;
import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.exceptions.OnlyBankException;
import br.com.bank.onlybank.repositories.ClientRepository;
import br.com.bank.onlybank.services.impl.GenericServiceImpl;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Optional;

@Service
public class ClientService extends GenericServiceImpl<Client, Long> {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserService userService;

    public ClientService(ClientRepository clientRepository) {
        super(clientRepository);
        this.clientRepository = clientRepository;
    }

    @Override
    public Client save(Client entity) {
        Client findCLient = null;

        try {
            findCLient = findByCpf(entity.getCpf());
        } catch (OnlyBankException e) {
            findCLient = super.save(entity);
        }
        return findCLient;
    }

    @Override
    protected Client update(Client entity, Client newEntity) {
        return null;
    }

    @Override
    public Client find(Long key) {
        userService.validateClientId(key);
        return super.find(key);
    }

    public Client findByCpf(String cpf) {
        userService.validateClientId(cpf);

        Optional<Client> client = clientRepository.findByCpf(cpf);

        return client.orElseThrow(() -> new OnlyBankException("Client Not Found! CPF: " + cpf));
    }

    public Client convertNewClientDTOToEntity(@Valid NewClientDTO newClientDTO) {
        return new Client(newClientDTO);
    }
}

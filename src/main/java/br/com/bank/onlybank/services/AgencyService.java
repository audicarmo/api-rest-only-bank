package br.com.bank.onlybank.services;

import br.com.bank.onlybank.dtos.AgencyDTO;
import br.com.bank.onlybank.dtos.NewAgencyDTO;
import br.com.bank.onlybank.entities.Agency;
import br.com.bank.onlybank.entities.Bank;
import br.com.bank.onlybank.exceptions.OnlyBankException;
import br.com.bank.onlybank.repositories.AgencyRepository;
import br.com.bank.onlybank.services.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgencyService extends GenericServiceImpl<Agency, Long> {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private AgencyDTO agencyDTO;

    public AgencyService(AgencyRepository agencyRepository) {
        super(agencyRepository);
        this.agencyRepository = agencyRepository;
    }

    @Override
    public Agency find(Long key) {
        Optional<Agency> agency = agencyRepository.findById(key);

        return agency.orElseThrow(() -> new OnlyBankException(
                "Object not Found! Id: " + key + ", Type: " + Agency.class.getName()));
    }

    @Override
    public Agency update(Agency newAgency) {
        Agency agency = find(newAgency.getId());
        newAgency = update(agency, newAgency);
        return agencyRepository.save(newAgency);
    }

    @Override
    protected Agency update(Agency entity, Agency newEntity) {
        Agency agency = new Agency(newEntity.getNumber(), newEntity.getName());
        agency.setId(entity.getId());
        agency.setBank(entity.getBank());

        return agency;
    }

    @Override
    public void delete(Long key) {
        find(key);

        try {
            agencyRepository.deleteById(key);
        }
        catch (OnlyBankException e) {
            throw new OnlyBankException("You cannot delete the agency that has accounts!");
        }
    }

    public Agency convertsDTOintoEntity(AgencyDTO agencyDTO) {
        return new Agency(agencyDTO.getNumber(), agencyDTO.getName());
    }

    public Agency convertsDTOintoEntity(NewAgencyDTO newAgencyDTO) {
        Agency agency = new Agency(agencyDTO.getNumber(), agencyDTO.getName());
        Bank bank = new Bank(agencyDTO.getBank().getId());
        agency.setBank(bank);

        return agency;
    }

    public Agency findByAgency(Long bankId, String number) {
        Optional<Agency> agency = agencyRepository.findByAgency(bankId, number);

        return agency.orElseThrow(() -> new OnlyBankException(
                "Agency not Found! Number: " + number));
    }

    public List<Agency> findNumber(Long id) {
        return agencyRepository.findNumber(id);
    }
}

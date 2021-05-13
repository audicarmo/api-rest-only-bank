package br.com.bank.onlybank.controllers;

import br.com.bank.onlybank.dtos.ClientDTO;
import br.com.bank.onlybank.dtos.NewClientDTO;
import br.com.bank.onlybank.entities.Client;
import br.com.bank.onlybank.services.ClientService;
import br.com.bank.onlybank.services.impl.GenericServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private GenericServiceImpl genericService;

    @GetMapping
    public ResponseEntity<List<ClientDTO>> list() {
        List<Client> clients = clientService.listAll();
        List<ClientDTO> clientDTOS = clients.stream().map(client -> new ClientDTO(client)).collect(Collectors.toList());

        return ResponseEntity.ok(clientDTOS);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> find(@PathVariable Long id) {
        Client findClient = clientService.find(id);

        if (findClient == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientDTO(findClient));
    }

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody NewClientDTO newClientDTO) {
        Client registeredcustomer = clientService.convertNewClientDTOToEntity(newClientDTO);
        registeredcustomer = clientService.save(registeredcustomer);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registeredcustomer.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody NewClientDTO clientDTO) {
        Client findClient = clientService.convertNewClientDTOToEntity(clientDTO);
        findClient.setId(id);
        findClient = clientService.update(findClient);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        clientService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClientDTO>> sort(@RequestParam(value = "page", defaultValue = "0") Integer page,
                                                @RequestParam(value = "linesPerPage", defaultValue = "30") Integer linesPerPage,
                                                @RequestParam(value = "orderBy", defaultValue = "name") String orderBy,
                                                @RequestParam(value="direction", defaultValue="ASC") String direction) {

    Page<Client> list = GenericServiceImpl.sort(page, linesPerPage, orderBy, direction);
    Page<ClientDTO> listDTO = list.map(obj -> new ClientDTO(obj));

    return ResponseEntity.ok().body(listDTO);
    }
}

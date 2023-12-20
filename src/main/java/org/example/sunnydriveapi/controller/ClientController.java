package org.example.sunnydriveapi.controller;

import org.example.sunnydriveapi.model.Client;
import org.example.sunnydriveapi.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public List<Client> getClients() {
        return clientService.getClients();
    }

    @GetMapping(path = "{clientId}")
    public Optional<Client> getClient(@PathVariable("clientId") Long clientId) {
        return clientService.getClient(clientId);
    }

    @PostMapping
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping(path = "{clientId}")
    public ResponseEntity<Optional<Client>> updateClient(@PathVariable Long clientId ,@RequestBody Client client) {
        return clientService.updateClient(clientId,client);
    }

    @DeleteMapping(path = "{clientId}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long clientId) {
        return clientService.deleteClient(clientId);
    }
}

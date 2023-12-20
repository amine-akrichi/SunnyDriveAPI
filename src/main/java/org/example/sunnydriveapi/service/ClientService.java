package org.example.sunnydriveapi.service;

import jakarta.transaction.Transactional;
import org.example.sunnydriveapi.model.Client;
import org.example.sunnydriveapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Client> addClient(Client client) {
        if (clientRepository.findByEmail(client.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Client with email " + client.getEmail() + " already exists");
        }
        return ResponseEntity.ok(clientRepository.save(client));
    }

    public Optional<Client> getClient(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public List<Client> getClients() {
        return clientRepository.findAll();
    }

    public ResponseEntity<Client> deleteClient(Long clientId) {
        Client clientToDelete = clientRepository.findById(clientId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + clientId + " does not exists")
        );
        clientRepository.deleteById(clientId);
        return ResponseEntity.ok(clientToDelete);
    }

    @Transactional
    public ResponseEntity<Optional<Client>> updateClient(Long clientId, Client client) {
        Client clientToUpdate = clientRepository.findById(clientId).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Client with id " + clientId + " does not exists")
        );
        if (client.getFirstname() != null && !client.getFirstname().isEmpty()) {
            clientToUpdate.setFirstname(client.getFirstname());
        }
        if (client.getLastname() != null && !client.getLastname().isEmpty()) {
            clientToUpdate.setLastname(client.getLastname());
        }
        if (client.getEmail() != null && !client.getEmail().isEmpty()) {
            clientToUpdate.setEmail(client.getEmail());
        }
        if (client.getPhone() != null && !client.getPhone().isEmpty()) {
            clientToUpdate.setPhone(client.getPhone());
        }
        if (client.getAddress() != null && !client.getAddress().isEmpty()) {
            clientToUpdate.setAddress(client.getAddress());
        }
        if (client.getCity() != null && !client.getCity().isEmpty()) {
            clientToUpdate.setCity(client.getCity());
        }
        if (client.getCountry() != null && !client.getCountry().isEmpty()) {
            clientToUpdate.setCountry(client.getCountry());
        }
        return ResponseEntity.ok(clientRepository.findById(clientId));

    }
}

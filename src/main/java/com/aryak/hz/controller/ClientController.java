package com.aryak.hz.controller;

import com.aryak.hz.service.ClientService;
import com.aryak.hz.model.Client;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientController {

    private final ClientService clientMetadataService;

    public ClientController(ClientService clientMetadataService) {
        this.clientMetadataService = clientMetadataService;
    }

    @GetMapping("/client")
    public Client getClient(@RequestParam Long clientId) {
        return clientMetadataService.getClient(clientId);
    }

    @PutMapping("/client")
    public void updateClient(@RequestParam Long clientId, @RequestParam String clientName) {
        Client client = new Client(clientId, clientName);
        clientMetadataService.updateClient(clientId, client);
    }
}



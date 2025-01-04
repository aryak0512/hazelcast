package com.aryak.hz.service;

import com.aryak.hz.model.Client;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final IMap<Long, Client> clientMetadataMap;

    @Autowired
    public ClientService(HazelcastInstance hazelcastInstance) {
        this.clientMetadataMap = hazelcastInstance.getMap("clientMetadata");
    }

    public Client getClient(Long clientId) {
        return clientMetadataMap.get(clientId); // Fast, distributed lookup
    }

    public void updateClient(Long clientId, Client client) {
        clientMetadataMap.put(clientId, client); // Update data
    }
}


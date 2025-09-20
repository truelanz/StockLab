package com.truelanz.StockLab.services;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.truelanz.StockLab.dto.ClientDTO;
import com.truelanz.StockLab.entities.Client;
import com.truelanz.StockLab.repositories.ClientRepository;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAllPaged(Pageable pageable) {
        Page<Client> result = repository.findAll(pageable);
        return result.map(x -> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
        // Salva movimentação
        Client client = new Client();
        client.setName(dto.getName());
        client.setPhone(dto.getPhone());
        client.setBirth(dto.getBirth());
        client.setDateRegister(Instant.now());
        client.setLocalAddress(dto.getLocalAddress());
        client.setCPF(dto.getCPF());

        // Atualiza produto
        repository.save(client);
        return new ClientDTO(client);
    }
}
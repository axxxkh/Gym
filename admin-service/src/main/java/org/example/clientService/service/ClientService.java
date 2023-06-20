package org.example.clientService.service;

import org.example.clientService.dto.ClientDTO;
import org.example.clientService.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {
    List<ClientDTO> getAll();
    ClientDTO addClient(ClientDTO clientDTO);
}

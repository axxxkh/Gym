package org.example.clientService.service.impl;

import lombok.AllArgsConstructor;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.entity.AccessLogs;
import org.example.clientService.entity.Client;
import org.example.clientService.repository.AccessLogsRepository;
import org.example.clientService.repository.ClientRepository;
import org.example.clientService.service.ClientService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private AccessLogsRepository accessLogsRepository;
    private ModelMapper mapper;

    @Override
    public List<ClientDTO> getAll() {

        Client clientDB = clientRepository.getReferenceById(2L);
        List<AccessLogs> accessLogsSet = clientDB.getAccessLogsSet();
        System.out.println(clientDB);

        AccessLogs currentAccessSession = accessLogsSet.stream()
                .filter(session -> session.getTimeEnd() == null)
                .findFirst()
                .orElse(new AccessLogs());

        if (currentAccessSession.getTimeStart() == null) {
            currentAccessSession.setClient(clientDB);
            currentAccessSession.setTimeStart(LocalDateTime.now())
            ;
        } else {
            currentAccessSession.setTimeEnd(LocalDateTime.now());
        }

        accessLogsSet.add(currentAccessSession);
        clientDB.setAccessLogsSet(accessLogsSet);
        clientRepository.save(clientDB);

        return clientRepository.findAll()
                .stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) {
       return mapper.map(clientRepository.save(mapper.map(clientDTO, Client.class)),ClientDTO.class);
    }
}

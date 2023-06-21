package org.example.adminService.service.impl;

import lombok.AllArgsConstructor;
import org.example.adminService.dto.AccessLogsDTO;
import org.example.adminService.dto.ClientDTO;
import org.example.adminService.dto.ClientLogsDTO;
import org.example.adminService.entity.AccessLogs;
import org.example.adminService.entity.Client;
import org.example.adminService.exceptions.UserNotFound;
import org.example.adminService.repository.AccessLogsRepository;
import org.example.adminService.repository.ClientLogsRepository;
import org.example.adminService.repository.ClientRepository;
import org.example.adminService.service.ClientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;
    private ClientLogsRepository clientLogsRepository;
    private AccessLogsRepository accessLogsRepository;
    private ModelMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public ClientDTO getByPhoneNumber(String phoneNumber) {
        return mapper.map(clientRepository.findByPhoneNumber(phoneNumber).orElseThrow(), ClientDTO.class);
    }

    @Override
    public ClientLogsDTO getLog() {
        return clientLogsRepository.findAll()
                .stream()
                .findFirst().map(x -> mapper.map(x, ClientLogsDTO.class)).orElseThrow();
    }

    private Client getClientFromDB(ClientDTO clientDTO) throws UserNotFound {
        String errorMessage = String.format("User with phone number %s not found", clientDTO.getPhoneNumber());

        return clientRepository
                .findByPhoneNumber(clientDTO.getPhoneNumber())
                .orElseThrow(() -> {
                    LOGGER.error(errorMessage);
                    return new UserNotFound(errorMessage);
                });
    }

    private Client getClientFromDB(String phoneNumber) throws UserNotFound {
        String errorMessage = String.format("User with phone number %s not found", phoneNumber);

        return clientRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> {
                    LOGGER.error(errorMessage);
                    return new UserNotFound(errorMessage);
                });
    }

    @Override
    public AccessLogsDTO checkIn(ClientDTO clientDTO) throws UserNotFound {
        String infoMessage;
        Client clientDB = getClientFromDB(clientDTO);

        List<AccessLogs> accessLogsSet = clientDB.getAccessLogsSet();
        AccessLogs currentAccessSession = accessLogsSet.stream()
                .filter(session -> session.getTimeEnd() == null)
                .findFirst()
                .orElse(new AccessLogs());

        if (currentAccessSession.getTimeStart() == null) {
            currentAccessSession.setClient(clientDB);
            currentAccessSession.setTimeStart(LocalDateTime.now());
            infoMessage = String.format("User %s started session at %s", clientDB.getId(), LocalDateTime.now());
        } else {
            currentAccessSession.setTimeEnd(LocalDateTime.now());
            infoMessage = String.format("User %s finished session at %s", clientDB.getId(), LocalDateTime.now());
        }

        accessLogsSet.add(currentAccessSession);
        clientDB.setAccessLogsSet(accessLogsSet);
        clientRepository.save(clientDB);
        LOGGER.info(infoMessage);
        return mapper.map(currentAccessSession, AccessLogsDTO.class);
    }
}

package org.example.clientService.service.impl;

import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.dto.ClientLogsDTO;
import org.example.clientService.entity.AccessLogs;
import org.example.clientService.entity.Client;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;
import org.example.clientService.repository.AccessLogsRepository;
import org.example.clientService.repository.ClientLogsRepository;
import org.example.clientService.repository.ClientRepository;
import org.example.clientService.service.AdminService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {

    private ClientRepository clientRepository;
    private ClientLogsRepository clientLogsRepository;
    private AccessLogsRepository accessLogsRepository;
    private ModelMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public List<ClientDTO> getAll() {

        Client clientDB = clientRepository.getReferenceById(2L);
        List<AccessLogs> accessLogsSet = clientDB.getAccessLogsSet();

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
    public ClientDTO getByPhoneNumber(String phoneNumber) {
        return null;
    }

    @Override
    public ClientDTO addClient(ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist {
        String errorMessage;
        try {
            getClientFromDB(clientDTO);
        } catch (UserNotFound ex) {
            return mapper.map(clientRepository.save(mapper.map(clientDTO, Client.class)), ClientDTO.class);
        }
        errorMessage = String.format("User with phone number %s already exist", clientDTO.getPhoneNumber());
        LOGGER.error(errorMessage);
        throw new UserAlreadyExist(errorMessage);
    }

    @Override
    public ClientDTO editClient(String phoneNumber, ClientDTO clientDTO) {
        return null;
    }

    @Override
    public ClientLogsDTO getLog() {
        return null;
    }

    @Override
    public List<AccessLogsDTO> getAccessLogByPeriod(String phoneNumber, LocalDate startDate, LocalDate endDate) throws UserNotFound {
        Client clientDB = getClientFromDB(phoneNumber);
        return accessLogsRepository.findByPeriod(startDate, endDate, clientDB.getId())
                .stream()
                .map(l -> mapper.map(l, AccessLogsDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ClientDTO> getBirthdayThisMonth() {
        return null;
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
}

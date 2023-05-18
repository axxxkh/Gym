package org.example.clientService.service.impl;

import lombok.AllArgsConstructor;
import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.dto.ClientDTO;
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
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private ClientRepository clientRepository;
    private ClientLogsRepository clientLogsRepository;
    private AccessLogsRepository accessLogsRepository;
    private ModelMapper mapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientServiceImpl.class);

    @Override
    public List<ClientDTO> getAll() {
        return clientRepository.findAll()
                .stream()
                .map((client -> mapper.map(client, ClientDTO.class)))
                .collect(Collectors.toList());

    }

    @Override
    public ClientDTO getByPhoneNumber(String phoneNumber) throws UserNotFound {
        String errorMessage = String.format("User with phone number %s not found", phoneNumber);

        return clientRepository
                .findByPhoneNumber(phoneNumber)
                .map(client -> mapper.map(client, ClientDTO.class))
                .orElseThrow(() -> {
                    LOGGER.error(errorMessage);
                    return new UserNotFound(errorMessage);
                });
    }

    @Override
    public List<AccessLogsDTO> getClientLogs(ClientDTO clientDTO) throws UserNotFound {
        Client clientDB = getClientFromDB(clientDTO);

        return clientDB.getAccessLogsSet()
                .stream()
                .map(accessLogs -> mapper.map(accessLogs, AccessLogsDTO.class))
                .collect(Collectors.toList());
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
    public ClientDTO addClient(ClientDTO clientDTO) throws UserAlreadyExist {
        String errorMessage = String.format("User with phone number %s already exist", clientDTO.getPhoneNumber());
        String infoMessage = "Added user" + clientDTO.toString();
        try {
            getClientFromDB(clientDTO);
        } catch (UserNotFound ex) {
            LOGGER.info(infoMessage);
            return mapper.map(clientRepository.save(mapper.map(clientDTO, Client.class)), ClientDTO.class);
        }
        LOGGER.error(errorMessage);
        throw new UserAlreadyExist(errorMessage);
    }

    @Override
    public ClientDTO editClient(String phoneNumber, ClientDTO clientDTO) throws UserNotFound {
        String errorMessage = String.format("User with phone number %s doesn't exist", phoneNumber);
        String infoMessage = String.format("Client with phone number %s updated with %s ", phoneNumber, clientDTO);
        Client oldClient;
        try {
            oldClient = getClientFromDB(phoneNumber);
        } catch (UserNotFound ex) {
            LOGGER.error(errorMessage);
            throw new UserNotFound(errorMessage);
        }
        oldClient.setName(clientDTO.getName());
        oldClient.setSurname(clientDTO.getSurname());
        oldClient.setPhoneNumber(clientDTO.getPhoneNumber());
        oldClient.setBirthdate(clientDTO.getBirthdate());
        oldClient.setAdditionalInfo(clientDTO.getAdditionalInfo());
        LOGGER.info(infoMessage);
        return mapper.map(clientRepository.saveAndFlush(oldClient), ClientDTO.class);
    }

    @Override
    public List<ClientDTO> getBirthdaysByPeriod(LocalDate startDate, LocalDate endDate) {
        return clientRepository.findByPeriod(startDate, endDate)
                .stream()
                .map(client -> mapper.map(client, ClientDTO.class))
                .collect(Collectors.toList());
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

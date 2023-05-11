package org.example.clientService.service;

import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.dto.ClientLogsDTO;
import org.example.clientService.entity.Client;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface ClientService {
    List<ClientDTO> getAll();
    ClientDTO getByPhoneNumber(String phoneNumber);
    ClientDTO addClient(ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist;
    ClientDTO editClient(String phoneNumber, ClientDTO clientDTO);
    ClientLogsDTO getLog();
    AccessLogsDTO checkIn(ClientDTO clientDTO) throws UserNotFound;
    List<AccessLogsDTO> getAccessLogByPeriod(String phoneNumber,LocalDate startDate, LocalDate endDate) throws UserNotFound;
}

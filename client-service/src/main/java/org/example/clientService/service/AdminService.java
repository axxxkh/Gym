package org.example.clientService.service;

import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.dto.ClientLogsDTO;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
    List<ClientDTO> getAll();
    ClientDTO getByPhoneNumber(String phoneNumber);
    ClientDTO addClient(ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist;
    ClientDTO editClient(String phoneNumber, ClientDTO clientDTO);
    ClientLogsDTO getLog();
    List<AccessLogsDTO> getAccessLogByPeriod(String phoneNumber, LocalDate startDate, LocalDate endDate) throws UserNotFound;
    List<ClientDTO> getBirthdayThisMonth ();
}

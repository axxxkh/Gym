package org.example.adminService.service;

import org.example.adminService.dto.AccessLogsDTO;
import org.example.adminService.dto.ClientDTO;
import org.example.adminService.exceptions.UserAlreadyExist;
import org.example.adminService.exceptions.UserNotFound;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {
//    works
    List<ClientDTO> getAll();
//    works
    ClientDTO getByPhoneNumber(String phoneNumber) throws UserNotFound;
//    works
    List<AccessLogsDTO> getClientLogs (ClientDTO clientDTO) throws UserNotFound;
//    works
    ClientDTO addClient(ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist;
//    works
    ClientDTO editClient(String phoneNumber, ClientDTO clientDTO) throws UserNotFound;
    List<AccessLogsDTO> getAccessLogByPeriod(String phoneNumber, LocalDate startDate, LocalDate endDate) throws UserNotFound;
//    works
    List<ClientDTO> getBirthdaysByPeriod(LocalDate startDate, LocalDate endDate);
}

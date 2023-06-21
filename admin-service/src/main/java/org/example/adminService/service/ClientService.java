package org.example.adminService.service;

import org.example.adminService.dto.AccessLogsDTO;
import org.example.adminService.dto.ClientDTO;
import org.example.adminService.dto.ClientLogsDTO;
import org.example.adminService.exceptions.UserNotFound;
import org.springframework.stereotype.Service;

@Service
public interface ClientService {
    ClientDTO getByPhoneNumber(String phoneNumber);
    ClientLogsDTO getLog();
    AccessLogsDTO checkIn(ClientDTO clientDTO) throws UserNotFound;
}

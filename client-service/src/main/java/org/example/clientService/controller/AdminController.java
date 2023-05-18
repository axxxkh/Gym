package org.example.clientService.controller;

import lombok.AllArgsConstructor;
import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;
import org.example.clientService.service.AdminService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/admin/")
public class AdminController {

    private AdminService adminService;

    // works
    @GetMapping("/client/")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(adminService.getAll());
    }

    // works
    @GetMapping("/client/logs")
    public ResponseEntity<List<AccessLogsDTO>> getClientLogs(@RequestBody ClientDTO clientDTO) throws UserNotFound {
        return ResponseEntity.ok(adminService.getClientLogs(clientDTO));
    }

    //  works
    @GetMapping("/client/phone")
    public ResponseEntity<ClientDTO> getByPhoneNumber(@RequestParam String phoneNumber) throws UserNotFound {
        return ResponseEntity.ok(adminService.getByPhoneNumber(phoneNumber));
    }

    //    works
    @PostMapping("/client/")
    public ResponseEntity<ClientDTO> addClient(@RequestBody ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist {
        return ResponseEntity.ok(adminService.addClient(clientDTO));
    }

    // works
    @PutMapping("/client/")
    public ResponseEntity<ClientDTO> editClient(@RequestParam String phoneNumber, @RequestBody ClientDTO clientDTO) throws UserNotFound {
        return ResponseEntity.ok(adminService.editClient(phoneNumber, clientDTO));
    }

    //    works
    @GetMapping("/client/birthdays")
    public ResponseEntity<List<ClientDTO>> getBirthdaysByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        return ResponseEntity.ok(adminService.getBirthdaysByPeriod(startDate, endDate));
    }

    @GetMapping("/period/")
    public ResponseEntity<List<AccessLogsDTO>> getAccessLogByPeriod(@RequestHeader("phoneNumber") String phoneNumber,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws UserNotFound {

        return ResponseEntity.ok(adminService.getAccessLogByPeriod(phoneNumber, startDate, endDate));
    }
}

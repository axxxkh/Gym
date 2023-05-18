package org.example.clientService.controller;

import lombok.AllArgsConstructor;
import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.service.AdminService;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;
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

    @GetMapping("/client/")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(adminService.getAll());
    }

    @GetMapping("/client2/")
    public ResponseEntity<ClientDTO> getByPhoneNumber (@RequestParam String phoneNumber) {
        return ResponseEntity.ok(adminService.getByPhoneNumber(phoneNumber));
    }

    @PostMapping("/client/")
    public ResponseEntity<ClientDTO> addClient (@RequestBody ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist {
        return ResponseEntity.ok(adminService.addClient(clientDTO));
    }

    @PutMapping("/client/")
    public ResponseEntity<ClientDTO> editClient (@RequestParam String phoneNumber, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(adminService.editClient(phoneNumber, clientDTO));
    }

    @GetMapping("/period/")
    public ResponseEntity<List<AccessLogsDTO>> getAccessLogByPeriod(@RequestHeader("phoneNumber") String phoneNumber,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) throws UserNotFound {

        return ResponseEntity.ok(adminService.getAccessLogByPeriod(phoneNumber, startDate, endDate));
    }
}

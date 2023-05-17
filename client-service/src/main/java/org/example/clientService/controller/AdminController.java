package org.example.clientService.controller;

import lombok.AllArgsConstructor;
import org.example.clientService.service.AdminService;
import org.modelmapper.ModelMapper;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;
import org.example.clientService.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}

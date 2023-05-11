package org.example.clientService.controller;

import lombok.AllArgsConstructor;
import org.apache.catalina.mapper.Mapper;
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

    private ClientService clientService;
    private Mapper mapper;

    @GetMapping("/client/")
    public ResponseEntity<List<ClientDTO>> getAllClients() {
        return ResponseEntity.ok(clientService.getAll());
    }

    @GetMapping("/client/")
    public ResponseEntity<ClientDTO> getByPhoneNumber (@RequestParam String phoneNumber) {
        return ResponseEntity.ok(clientService.getByPhoneNumber(phoneNumber));
    }

    @PostMapping("/client/")
    public ResponseEntity<ClientDTO> addClient (@RequestBody ClientDTO clientDTO) throws UserNotFound, UserAlreadyExist {
        return ResponseEntity.ok(clientService.addClient(clientDTO));
    }

    @PostMapping("/client/")
    public ResponseEntity<ClientDTO> editClient (@RequestParam String phoneNumber, @RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.editClient(phoneNumber, clientDTO));
    }
}

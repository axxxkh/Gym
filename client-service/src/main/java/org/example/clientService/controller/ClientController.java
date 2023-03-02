package org.example.clientService.controller;

import lombok.AllArgsConstructor;

import org.example.clientService.dto.ClientDTO;
import org.example.clientService.entity.AccessLogs;
import org.example.clientService.entity.Client;
import org.example.clientService.repository.ClientRepository;
import org.example.clientService.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
//@RequestMapping("/")
public class ClientController {

    private ClientService clientService;

    @GetMapping("/get")
    public ResponseEntity<List<ClientDTO>> getClient () {

        return ResponseEntity.ok(clientService.getAll());
    }

    @PostMapping("/add")
    public ResponseEntity<ClientDTO> addClient (@RequestBody ClientDTO clientDTO) {
        return ResponseEntity.ok(clientService.addClient(clientDTO));
    }
}

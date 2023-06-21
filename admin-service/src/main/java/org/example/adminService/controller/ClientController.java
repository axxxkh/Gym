package org.example.adminService.controller;

import lombok.AllArgsConstructor;
import org.example.adminService.dto.AccessLogsDTO;
import org.example.adminService.dto.ClientDTO;
import org.example.adminService.dto.ClientLogsDTO;
import org.example.adminService.exceptions.UserNotFound;
import org.example.adminService.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class ClientController {

    private ClientService clientService;

    @GetMapping("/gett/")
    public ResponseEntity<ClientDTO> getClient() {
        return ResponseEntity.ok(clientService.getByPhoneNumber("1234344343"));
    }


    @GetMapping("/log/")
    public ResponseEntity<ClientLogsDTO> getClientLog() {
        System.out.println(clientService.getLog());
        return ResponseEntity.ok(clientService.getLog());
    }

    @PostMapping("/checkIn/")
    public ResponseEntity<AccessLogsDTO> checkIn(@RequestBody ClientDTO clientDTO) throws UserNotFound {

        return ResponseEntity.ok(clientService.checkIn(clientDTO));
    }
}

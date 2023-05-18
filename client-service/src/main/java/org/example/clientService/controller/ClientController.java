package org.example.clientService.controller;

import lombok.AllArgsConstructor;
import org.example.clientService.dto.AccessLogsDTO;
import org.example.clientService.dto.ClientDTO;
import org.example.clientService.dto.ClientLogsDTO;
import org.example.clientService.exceptions.UserAlreadyExist;
import org.example.clientService.exceptions.UserNotFound;
import org.example.clientService.service.ClientService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

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

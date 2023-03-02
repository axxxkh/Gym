package org.example.userAuthentication.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.userAuthentication.dto.LoginRequest;
import org.example.userAuthentication.dto.LoginResponse;
import org.example.userAuthentication.dto.RegisterRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Data
@AllArgsConstructor
public class LoginController {

    @PostMapping("user/login")
    public ResponseEntity<LoginResponse> userLogin(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok().body(LoginResponse.builder().build());
    }

    @PostMapping("admin/login")
    public ResponseEntity<LoginResponse> adminLogin(@RequestBody @Valid LoginRequest request) {
        return ResponseEntity.ok().body(LoginResponse.builder().build());
    }

    @PostMapping("user/register")
    public ResponseEntity<LoginResponse> userRegister(@RequestBody @Valid RegisterRequest request) {
        return ResponseEntity.ok().body(LoginResponse.builder().build());
    }
}

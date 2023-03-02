package org.example.userAuthentication.dto;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class LoginResponse {
    @NotNull
    private String id;
    @NotNull
    private String token;
    @NotNull
    private Role role;
}

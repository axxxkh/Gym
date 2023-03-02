package org.example.userAuthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@Validated
public class LoginRequest {
    @NotEmpty(message = "Phone number can`t be blank")
    @Length(min = 10, max = 10, message = "Wrong type of phone number, should be just phone number without country code, and another symbols (spaces etc")
    private String phoneNumber;
    @NotEmpty(message = "Password can`t be empty")
    private String password;
}

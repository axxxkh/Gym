package org.example.userAuthentication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class RegisterRequest {
    @NotEmpty(message = "Phone number can`t be blank")
    @Length(min=10, max = 10, message = "Wrong type of phone number")
    private String phoneNumber;
    @NotEmpty(message = "Password can`tt be blank")
    private String password;
    @NotEmpty(message = "Name can`t be blank")
    private String name;
    @NotEmpty(message = "Surname can`t be blank")
    private String surname;
    @NotNull(message = "Birthdate can`t be blank")
    private LocalDate birthday;
}

package org.example.adminService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String phoneNumber;
    private String name;
    private String surname;
    private LocalDate birthdate;
    private String additionalInfo;
}

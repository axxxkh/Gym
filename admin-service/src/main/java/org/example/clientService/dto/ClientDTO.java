package org.example.clientService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.clientService.entity.TariffPlan;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String phoneNumber;
    private String name;
    private LocalDateTime birthdate;
    private String tariffPlan;
    private Set<AccessLogsDTO> accessLogsSet;
}

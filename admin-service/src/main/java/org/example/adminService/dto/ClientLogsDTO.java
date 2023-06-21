package org.example.adminService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.adminService.entity.TariffPlan;

import java.time.LocalDate;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientLogsDTO {
    private String client;
    private TariffPlan tariffPlan;
    private Integer payment;
    private LocalDate timeStartTariff;
    private LocalDate timeEndTariff;
}

package org.example.adminService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffPlanDTO {
    private String name;
    private Integer price;
    private Integer durationMonth;
    private Boolean monday;
    private Boolean tuesday;
    private Boolean wednesday;
    private Boolean thursday;
    private Boolean friday;
    private Boolean saturday;
    private Boolean sunday;
    private Time startTime;
    private Time finishTime;
}

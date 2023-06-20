package org.example.clientService.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Time;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TariffPlanDTO {
    private String name;
    private Integer price;
    private Time monthDuration;
}

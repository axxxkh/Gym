package org.example.clientService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Time;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TariffPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name",nullable = false)
    private String name;
    @Column(name = "price",nullable = false)
    private Integer price;
    @Column(name = "Month_duration",nullable = false)
    private Time monthDuration;

    @Override
    public String toString() {
        return "TariffPlan{" +
                "name='" + name + '\'' +
                '}';
    }
}

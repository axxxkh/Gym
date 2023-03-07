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
    @Column(name = "Duration_month",nullable = false)
    private Integer durationMonth;
    @Column(name = "Monday",nullable = false)
    private Boolean monday;
    @Column(name = "Tuesday",nullable = false)
    private Boolean tuesday;
    @Column(name = "Wednesday",nullable = false)
    private Boolean wednesday;
    @Column(name = "Thursday",nullable = false)
    private Boolean thursday;
    @Column(name = "Friday",nullable = false)
    private Boolean friday;
    @Column(name = "Saturday",nullable = false)
    private Boolean saturday;
    @Column(name = "Sunday",nullable = false)
    private Boolean sunday;
    @Column(name = "Start_time",nullable = false)
    private Time startTime;
    @Column(name = "Finish_time",nullable = false)
    private Time finishTime;

    @Override
    public String toString() {
        return "TariffPlan{" +
                "name='" + name + '\'' +
                '}';
    }
}

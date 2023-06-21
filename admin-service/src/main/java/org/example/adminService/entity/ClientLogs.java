package org.example.adminService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Client_logs")
public class ClientLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "CLIENT_ID", referencedColumnName = "ID", nullable = false)
    private Client client;
    @ManyToOne
    @JoinColumn(name = "TARIFF_PLAN_ID",referencedColumnName = "ID",nullable = false)
    private TariffPlan tariffPlan;
    @Column(name = "payment", nullable = false)
    private Integer payment;
    @Column(name = "Time_start_tariff", nullable = false)
    private LocalDate timeStartTariff;
    @Column(name = "Time_end_tariff", nullable = false)
    private LocalDate timeEndTariff;

}

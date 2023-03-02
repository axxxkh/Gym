package org.example.clientService.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccessLogs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Time_Start")
    private LocalDateTime timeStart;
    @Column(name = "Time_end")
    private LocalDateTime timeEnd;
    @Column(name = "duration", nullable = true, insertable = false, updatable = false)
    private Long duration;
    @ManyToOne
    @JoinColumn(name = "FK_CLIENT_ID",referencedColumnName = "ID",nullable = false)
    private Client client;
}

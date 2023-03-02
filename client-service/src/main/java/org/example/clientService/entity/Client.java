package org.example.clientService.entity;

import lombok.*;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@Table(name="Client")
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "phone_number", unique = true, length = 10, nullable = false)
    private String phoneNumber;
    @Column(name = "Birthdate", nullable = false)
    private LocalDateTime birthdate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Tariffplan_id", columnDefinition = "BIGINT DEFAULT 1")
    private TariffPlan tariffPlan;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    @ToString.Exclude
    private List<AccessLogs> accessLogsSet;

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", birthdate=" + birthdate +
                ", tariffPlan=" + tariffPlan +
                '}';
    }
}

package org.example.clientService.entity;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.persistence.Table;
import java.time.LocalDate;
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
    @Column(name = "Surname", nullable = false)
    private String surname;
    @NaturalId
    @Column(name = "phone_number", unique = true, length = 10, nullable = false)
    private String phoneNumber;
    @Column(name = "Birthdate", nullable = false)
    private LocalDate birthdate;
    @Column(name = "Additional_info")
    private String additionalInfo;

    @OneToMany (cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
    @ToString.Exclude
    private List<AccessLogs> accessLogsSet;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "client")
//    @ToString.Exclude
//    private List<>
}

package org.example.adminService.repository;


import org.example.adminService.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByPhoneNumber(String phoneNumber);
    @Query(value = "SELECT * FROM Client c WHERE c.BIRTHDATE>= ?1 and c.BIRTHDATE <= ?2", nativeQuery = true)
    List<Client> findByPeriod(LocalDate startDate, LocalDate endDate);
}

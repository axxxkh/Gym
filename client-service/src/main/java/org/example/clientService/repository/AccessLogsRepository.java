package org.example.clientService.repository;

import org.example.clientService.entity.AccessLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AccessLogsRepository extends JpaRepository<AccessLogs, Long> {
    @Query(value = "SELECT * FROM Access_Logs a WHERE a.time_start>= ?1 and a.time_end <= ?2 and a.fk_client_id=?3", nativeQuery = true)
    List<AccessLogs> findByPeriod(LocalDate startDate, LocalDate endDate, Long id);
}

package org.example.clientService.repository;

import org.example.clientService.entity.AccessLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccessLogsRepository extends JpaRepository<AccessLogs, Long> {
}

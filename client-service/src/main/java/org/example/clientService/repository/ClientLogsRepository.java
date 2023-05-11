package org.example.clientService.repository;

import org.example.clientService.entity.ClientLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientLogsRepository extends JpaRepository<ClientLogs,Long> {
}

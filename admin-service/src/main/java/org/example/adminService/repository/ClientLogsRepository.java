package org.example.adminService.repository;

import org.example.adminService.entity.ClientLogs;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientLogsRepository extends JpaRepository<ClientLogs,Long> {
}

package org.example.clientService.repository;

import org.example.clientService.entity.TariffPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TariffPlanRepository extends JpaRepository<TariffPlan,Long> {
}

package com.vsm.vehicle_service_management.repository;

import com.vsm.vehicle_service_management.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, Long> {
}
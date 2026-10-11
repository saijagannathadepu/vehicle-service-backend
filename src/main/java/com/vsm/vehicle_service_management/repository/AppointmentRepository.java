package com.vsm.vehicle_service_management.repository;

import com.vsm.vehicle_service_management.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository
        extends JpaRepository<Appointment, Long> {
}
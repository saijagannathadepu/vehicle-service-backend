package com.vsm.vehicle_service_management.service;

import com.vsm.vehicle_service_management.entity.Appointment;
import com.vsm.vehicle_service_management.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(
            AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Retrieve all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Retrieve one appointment by ID
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id);
    }

    // Create or update an appointment
    public Appointment saveAppointment(Appointment appointment) {
        return appointmentRepository.save(appointment);
    }

    // Delete an appointment
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}
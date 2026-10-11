package com.vsm.vehicle_service_management.controller;

import com.vsm.vehicle_service_management.entity.Appointment;
import com.vsm.vehicle_service_management.service.AppointmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(
            AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Get all appointments
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        return ResponseEntity.ok(
                appointmentService.getAllAppointments());
    }

    // Get appointment by ID
    @GetMapping("/{id}")
    public ResponseEntity<Appointment> getAppointmentById(
            @PathVariable Long id) {

        return appointmentService.getAppointmentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Create appointment
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(
            @RequestBody Appointment appointment) {

        appointment.setId(null);

        if (appointment.getStatus() == null
                || appointment.getStatus().isBlank()) {
            appointment.setStatus("Pending");
        }

        Appointment savedAppointment = appointmentService.saveAppointment(appointment);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedAppointment);
    }

    // Update appointment
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        return appointmentService.getAppointmentById(id)
                .map(existing -> {
                    existing.setCustomerName(
                            appointment.getCustomerName());
                    existing.setVehicleNumber(
                            appointment.getVehicleNumber());
                    existing.setServiceName(
                            appointment.getServiceName());
                    existing.setAppointmentDate(
                            appointment.getAppointmentDate());
                    existing.setAppointmentTime(
                            appointment.getAppointmentTime());

                    if (appointment.getStatus() != null
                            && !appointment.getStatus().isBlank()) {
                        existing.setStatus(appointment.getStatus());
                    }

                    Appointment updated = appointmentService.saveAppointment(existing);

                    return ResponseEntity.ok(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Delete appointment
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(
            @PathVariable Long id) {

        if (appointmentService.getAppointmentById(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
package com.vsm.vehicle_service_management.controller;

import com.vsm.vehicle_service_management.entity.Service;
import com.vsm.vehicle_service_management.service.ServiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin
public class ServiceController {

    private final ServiceService serviceService;

    public ServiceController(ServiceService serviceService) {
        this.serviceService = serviceService;
    }

    @GetMapping
    public ResponseEntity<List<Service>> getAllServices() {
        return ResponseEntity.ok(serviceService.getAllServices());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Service> getServiceById(@PathVariable Long id) {
        return serviceService.getServiceById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Service> addService(@RequestBody Service service) {
        return ResponseEntity.ok(serviceService.addService(service));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Service> updateService(
            @PathVariable Long id,
            @RequestBody Service service) {

        return serviceService.getServiceById(id)
                .map(existingService -> {
                    existingService.setServiceName(service.getServiceName());
                    existingService.setDescription(service.getDescription());
                    existingService.setPrice(service.getPrice());
                    existingService.setEstimatedDuration(
                            service.getEstimatedDuration());

                    return ResponseEntity.ok(
                            serviceService.addService(existingService));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        try {
            serviceService.deleteService(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
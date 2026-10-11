package com.vsm.vehicle_service_management.service;

import com.vsm.vehicle_service_management.entity.Service;
import com.vsm.vehicle_service_management.repository.ServiceRepository;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceRepository serviceRepository;

    public ServiceService(ServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<com.vsm.vehicle_service_management.entity.Service> getAllServices() {
        return serviceRepository.findAll();
    }

    public Optional<com.vsm.vehicle_service_management.entity.Service> getServiceById(Long id) {
        return serviceRepository.findById(id);
    }

    public com.vsm.vehicle_service_management.entity.Service addService(
            com.vsm.vehicle_service_management.entity.Service service) {
        return serviceRepository.save(service);
    }

    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
}
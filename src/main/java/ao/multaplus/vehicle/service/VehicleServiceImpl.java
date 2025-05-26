package ao.multaplus.vehicle.service;

import ao.multaplus.state.entity.Status;
import ao.multaplus.state.service.StatusService;
import ao.multaplus.typeVehicle.entity.TypeVehicles;
import ao.multaplus.typeVehicle.service.TypeVehicleServiceImpl;
import ao.multaplus.vehicle.dtos.RegisteVehicleDto;
import ao.multaplus.vehicle.entity.Vehicles;
import ao.multaplus.vehicle.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;

    private final StatusService statusService;
    private final TypeVehicleServiceImpl typeVehicleService;

    @Override
    @Transactional
    public Vehicles registerVehicle(RegisteVehicleDto registeVehicleDto) {
        TypeVehicles typeVehicles =
                typeVehicleService.getTypeVehicles(registeVehicleDto.vehicleTypeId());
        Status status = statusService.getStatus(1L);
        Vehicles vehicle = Vehicles.builder()
                .plateNumber(registeVehicleDto.plateNumber())
                .registration(registeVehicleDto.color())
                .typeVehicles(typeVehicles)
                .color(registeVehicleDto.color())
                .brand(registeVehicleDto.brand())
                .state(status)
                .build();
      return  vehicleRepository.save(vehicle);
    }

    @Override
    @Transactional
    public void updateVehicle(String plate, RegisteVehicleDto vehicle) {
        Vehicles vehicles = getVehicle(plate);
        try {
            boolean updated = false;
            if (vehicle.vehicleTypeId() != null && !vehicle.vehicleTypeId().equals(
                    vehicles.getTypeVehicles().getId())) {
                vehicles.setTypeVehicles(
                        typeVehicleService.getTypeVehicles(vehicle.vehicleTypeId()));
                updated = true;
            }
            if (!plate.equals(vehicles.getPlateNumber())) {
                vehicles.setPlateNumber(plate);
                updated = true;
            }
            if (vehicle.vehicleTypeId() != null && !vehicle.vehicleTypeId().equals(
                    vehicles.getTypeVehicles().getId())) {
                vehicles.setTypeVehicles(
                        typeVehicleService.getTypeVehicles(vehicle.vehicleTypeId()));
                updated = true;
            }
            if (updated)
                vehicleRepository.save(vehicles);
        } catch (Exception e) {
            throw new RuntimeException("update-ve");
        }
    }

    @Override
    @Transactional
    public void removeVehicle(String plate) {
        Vehicles vehicles = getVehicle(plate);
        if (vehicles.getState().getId().equals(3L))
            throw new RuntimeException("vehicle already deleted");
        vehicles.setState(statusService.getStatus(3L));
        vehicleRepository.save(vehicles);
    }
    @Transactional
    public  Vehicles GetOrRegistVehicle(RegisteVehicleDto registeVehicleDto) {
        Vehicles vehicles = vehicleRepository.findVehicles(registeVehicleDto.plateNumber()).orElse(null);
        if (vehicles == null) {
            vehicles = registerVehicle(registeVehicleDto);
        }
        return vehicles;
    }

    @Override
    public Vehicles getVehicle(String plate) {
        return vehicleRepository.findVehicles(plate).orElseThrow(
                () -> new RuntimeException("vehicle not found"));
    }

}

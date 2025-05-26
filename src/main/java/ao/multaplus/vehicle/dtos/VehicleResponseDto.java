package ao.multaplus.vehicle.dtos;

import ao.multaplus.typeVehicle.dtos.VehiclesTypeDto;

public record VehicleResponseDto(
    String plateNumber,
    String color,
    String brand,
    VehiclesTypeDto vehiclesTyDetails
) {
}

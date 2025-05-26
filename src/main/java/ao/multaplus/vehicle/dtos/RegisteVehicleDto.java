package ao.multaplus.vehicle.dtos;

public record RegisteVehicleDto(
    String color,
    String brand,
    String plateNumber,
    Long vehicleTypeId,
    String registration
) {
}

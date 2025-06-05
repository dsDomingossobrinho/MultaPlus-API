package ao.multaplus.fine.dtos;

import ao.multaplus.Infringement.dtos.TypeInfringementsDto;
import ao.multaplus.Infringement.entity.Infringements;
import ao.multaplus.vehicle.dtos.VehicleResponseDto;
import ao.multaplus.vehicle.entity.Vehicles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record FineResponseDto(
        Long id,
        String description,
        LocalDateTime dateIssued,
        Integer daysToDue,
        NameAndIdDto motorist,
        NameAndIdDto agent,
        VehicleResponseDto vehicle,
        List<TypeInfringementsDto> infringements,
        String paymentReference
) {
}

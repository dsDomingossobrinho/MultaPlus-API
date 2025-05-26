package ao.multaplus.fine.dtos;

import ao.multaplus.Infringement.dtos.InfrigementNameAndAIDto;
import ao.multaplus.vehicle.dtos.RegisteVehicleDto;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AddFineDto(
        @NotEmpty(message = "Motorist identifier must be provided")
        String motoristIdentifier,
        String description,
        RegisteVehicleDto vehicleDetails,
        List<InfrigementNameAndAIDto> infringements,
        @NotEmpty(message = "Days to pay must be provider")
        @Min(value = 1, message = "Days to pay must be between 1 and 365")
        @Max(value = 365, message = "Days to pay must be between 1 and 365")
        Integer daysTOPay
) {
}

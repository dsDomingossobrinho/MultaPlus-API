package ao.multaplus.province.dtos;

import ao.multaplus.status.entity.Status;
import jakarta.validation.constraints.NotBlank;

public record ProvincesResponsedtos(
        Long id,
        String province,
        ao.multaplus.status.entity.Status stateId
) {

}

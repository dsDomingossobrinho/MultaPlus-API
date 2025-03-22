package ao.multaplus.province.response;

import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;

public record ProvinceResponse(
        Long id,
        String province,
        StateSenderDto state
) {

}

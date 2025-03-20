package ao.multaplus.typeUser.response;

import ao.multaplus.state.dtos.StateSenderDto;
import ao.multaplus.state.entity.Status;

public record TypeUsersResponse(
        Long id,
        String type,
        String description,
        StateSenderDto state
) {
}

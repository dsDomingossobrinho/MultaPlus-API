package ao.multaplus.Infringement.response;

import ao.multaplus.state.dtos.StateSenderDto;

public record TypeInfringementsResponse(
        Long id,
        String type,
        String description,
        double price,
        StateSenderDto state
) {
}

package ao.multaplus.typeInfringement.dtos;

import ao.multaplus.state.dtos.StateSenderDto;

public record TypeInfringementsUpdateDto(
        Long id,
        String type,
        String description,
        Long price,
        StateSenderDto state
) {
}

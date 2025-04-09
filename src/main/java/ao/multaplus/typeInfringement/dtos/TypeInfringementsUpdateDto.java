package ao.multaplus.typeInfringement.dtos;

import ao.multaplus.state.dtos.StateSenderDto;

public record TypeInfringementsUpdateDto(
        Long id,
        String type,
        String description,
        float price,
        StateSenderDto state
) {
}

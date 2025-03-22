package ao.multaplus.typeInfringement.dtos;


import ao.multaplus.state.dtos.StateSenderDto;

public record TypeInfringementsDto(
        String type,
        String description,
        Float price
) {
}

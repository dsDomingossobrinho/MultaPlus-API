package ao.multaplus.typeInfringement.DTO;

public record TypeInfringementsDTO(
        Long id,
        String type,
        String description,
        Float price,
        Long stateId
) {
}

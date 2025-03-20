package ao.multaplus.typeInfringement.dtos;

public record TypeInfringementsResponsedtos(
        Long id,
        String type,
        String description,
        Float price,
        ao.multaplus.status.entity.Status stateId
) {
}

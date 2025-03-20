package ao.multaplus.typeInfringement.dtos;

public record TypeInfringementsRequestdtos(
        String type,
        String description,
        Long price,
        Long statusId
) {
}

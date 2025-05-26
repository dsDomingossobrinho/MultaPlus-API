package ao.multaplus.Infringement.dtos;

public record InfringementsUpdateDto(
        Long id,
        String name,
        String description,
        float price,
        Long stateId
) {
}

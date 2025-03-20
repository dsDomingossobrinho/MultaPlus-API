package ao.multaplus.typeUser.dtos;

public record TypeUsersResponsedtos(
        Long id,
        String type,
        String description,
        ao.multaplus.status.entity.Status stateId
) {
}

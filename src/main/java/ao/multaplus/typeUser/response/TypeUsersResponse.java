package ao.multaplus.typeUser.response;

public record TypeUsersResponse(
        Long id,
        String type,
        String description,
        ao.multaplus.state.dtos.StateDto state
) {
}

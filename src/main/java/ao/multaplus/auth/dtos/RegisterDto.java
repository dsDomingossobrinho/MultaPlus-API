package ao.multaplus.auth.dtos;

public record RegisterDto(
    String email,
    String password,
    String role
) {
}

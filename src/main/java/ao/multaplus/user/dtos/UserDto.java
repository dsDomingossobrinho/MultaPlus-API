package ao.multaplus.user.dtos;

import ao.multaplus.auth.dtos.LoginDto;

import java.time.LocalDate;

public record UserDto(
        String name,
        LocalDate dateBirth,
        String telephone,
        String bi,
        String img,
        String nIdentification,
        Long genderId,
        Long stateId,
        Long provinceId,
        Long typeUserId,
        LoginDto authentication
) {
}

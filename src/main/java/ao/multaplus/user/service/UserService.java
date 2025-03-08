package ao.multaplus.user.service;

import ao.multaplus.role.entity.Roles;
import ao.multaplus.user.dtos.UserDto;
import ao.multaplus.user.entity.Users;

public interface UserService {

    Users create(UserDto userDto, Roles role);

    Users update(UserDto userDto);

    String delete(Long id);
}

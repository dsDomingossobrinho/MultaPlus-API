package ao.multaplus.role.service;

import ao.multaplus.role.entity.Roles;

public interface RoleService {

    void migration();

    Roles findById(Long id);

    Roles findByRole(String role);
}

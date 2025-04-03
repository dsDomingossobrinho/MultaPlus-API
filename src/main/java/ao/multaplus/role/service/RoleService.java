package ao.multaplus.role.service;

import ao.multaplus.action.dto.ActionRequestDto;
import ao.multaplus.role.dto.RoleRequestDto;
import ao.multaplus.role.entity.Roles;
import org.springframework.http.ResponseEntity;

public interface RoleService {

    void migration();

    ResponseEntity<?> getone(long id);

    Roles findByRole(String role);
    ResponseEntity<?> getall();
    ResponseEntity<?> save(RoleRequestDto save);
    ResponseEntity<?> update(long id,RoleRequestDto save);
    ResponseEntity<?> delete(long id);
}

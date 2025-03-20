package ao.multaplus.typeUser.service;

import ao.multaplus.typeUser.dtos.TypeUsersRequestdtos;
import ao.multaplus.typeUser.dtos.TypeUsersResponsedtos;

import java.util.List;

public interface TypeUserService {
    TypeUsersResponsedtos create(TypeUsersRequestdtos requestdtos);
    TypeUsersResponsedtos update(Long id,TypeUsersRequestdtos requestdtos);
    void delete(Long id);
    List<TypeUsersResponsedtos> getAll();
    TypeUsersResponsedtos getById(Long id);
    void migration();
}

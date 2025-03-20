package ao.multaplus.typeUser.service;

import ao.multaplus.role.entity.Roles;
import ao.multaplus.typeUser.repository.TypeUserRepository;
import ao.multaplus.typeUser.entity.TypeUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeUserServiceImpl implements TypeUserService {

    private final TypeUserRepository repository;

    @Override
    public void migration() {
        if (repository.count() == 0) {
            String[] array ={"admin", "Traffic regulatory agent"};

            for (int i = 0; i < array.length; i++) {
                TypeUsers typeUser = new TypeUsers();
                typeUser.setType(array[i]);
                repository.save(typeUser);
            }
        }
    }
}

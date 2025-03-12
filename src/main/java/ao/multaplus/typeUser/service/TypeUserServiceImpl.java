package ao.multaplus.typeUser.service;

import ao.multaplus.status.entity.Status;
import ao.multaplus.status.repository.StatusRepository;
import ao.multaplus.typeUser.repository.TypeUserRepository;
import ao.multaplus.typeUser.entity.TypeUsers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TypeUserServiceImpl implements TypeUserService {

    @Autowired
    private TypeUserRepository repository;

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

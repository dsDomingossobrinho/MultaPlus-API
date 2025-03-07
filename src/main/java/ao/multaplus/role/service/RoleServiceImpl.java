package ao.multaplus.role.service;

import ao.multaplus.role.entity.Roles;
import ao.multaplus.role.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository repository;

    @Override
    @PostConstruct
    public void migration() {
        if (repository.count() == 0) {
            String[] array ={"admin", "user"};

            for (int i = 0; i < array.length; i++) {
                Roles role = new Roles();
                //role.setId((long) i + 1L);
                role.setRole(array[i]);
                repository.save(role);
            }
        }
    }

    @Override
    public Roles findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Roles findByRole(String role) {
        return repository.findByRole(role).orElse(null);
    }
}

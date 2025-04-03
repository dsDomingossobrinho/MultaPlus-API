package ao.multaplus.role.service;

import ao.multaplus.role.dto.RoleRequestDto;
import ao.multaplus.role.entity.Roles;
import ao.multaplus.role.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<?> getone(long id) {
        Optional<Roles> roles=repository.findById(id);
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @Override
    public Roles findByRole(String role) {
        return repository.findByRole(role).orElse(null);
    }

    @Override
    public ResponseEntity<?> getall() {
        List<Roles> roles=repository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> save(RoleRequestDto save) {
        if (save==null){
            return new ResponseEntity<>("Cannot be empty",HttpStatus.CREATED);
        }
        Roles roles=new Roles();
        roles.setRole(save.role());
        roles.setDescription(save.description());
        repository.save(roles);
        return new ResponseEntity<>("Saved with Success",HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> update(long id, RoleRequestDto save) {
        Optional<Roles> roles=repository.findById(id);
        if (roles.isEmpty()){
            return new ResponseEntity<>("Not found",HttpStatus.NOT_FOUND);
        }
        roles.orElseThrow().setId(id);
        roles.orElseThrow().setRole(save.role());
        roles.orElseThrow().setDescription(save.description());
        Roles roles1=roles.get();
        repository.save(roles1);
        return new ResponseEntity<>("Updated With Success",HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> delete(long id) {
        repository.deleteById(id);
        return new ResponseEntity<>("Deleted with success",HttpStatus.OK);
    }
}

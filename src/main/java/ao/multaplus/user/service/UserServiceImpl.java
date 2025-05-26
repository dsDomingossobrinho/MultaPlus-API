package ao.multaplus.user.service;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.repository.AuthRepository;
import ao.multaplus.exception.model.ResourceInConflictException;
import ao.multaplus.role.entity.Roles;
import ao.multaplus.role.service.RoleService;
import ao.multaplus.user.dtos.UserDto;
import ao.multaplus.user.entity.Users;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final AuthRepository authRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users create(UserDto userDto, Roles role) {
        if (authRepository.findByEmail(userDto.authentication().email()) != null) {
            throw new ResourceInConflictException(
                    "Já existe um usuário com este e-mail: " + userDto.authentication().email());
        }
        Roles adminRole = roleService.findByRole("admin");
        Users user = new Users();
        user.setName(userDto.name());
        user.setDateBirth(userDto.dateBirth());
        user.setBi(userDto.bi());
        user.setImg(userDto.img());
        user.setNIdentification(userDto.nIdentification());
        Auth auth = Auth.builder()
                .email(userDto.authentication().email())
                .password(passwordEncoder.encode(userDto.authentication().password()))
                .role(adminRole)
                .telephone(userDto.telephone())
                .users(user)
                .build();
        authRepository.save(auth);
        return user;
    }

    @Override
    public Users update(UserDto userDto) {
        return null;
    }

    @Override
    public String delete(Long id) {
        return "";
    }


    @PostConstruct
    public void createAdminUsers() {
        Roles adminRole = roleService.findByRole("admin");
        String[][] users = {
                {"Maria Silva", "maria.silva@multaplus.ao"},
                {"João Pereira", "joao.pereira@multaplus.ao"},
                {"Ana Costa", "ana.costa@multaplus.ao"},
                {"Carlos Ramos", "carlos.ramos@multaplus.ao"},
                {"Lucia Fernandes", "lucia.fernandes@multaplus.ao"},
                {"Pedro Gomes", "pedro.gomes@multaplus.ao"},
                {"Isabel Andrade", "isabel.andrade@multaplus.ao"},
                {"Bruno Tavares", "bruno.tavares@multaplus.ao"},
                {"Sandra Lopes", "sandra.lopes@multaplus.ao"},
                {"Miguel Matos", "miguel.matos@multaplus.ao"}
        };

        for (int i = 0; i < users.length; i++) {
            String name = users[i][0];
            String email = users[i][1];

            if (authRepository.findByEmail(email) == null) {
                UserDto userDto = new UserDto(name,
                        LocalDate.of(1990, (i % 12) + 1, (i % 28) + 1),
                        "92300000" + i,
                        "BI000000" + i,
                        name.toLowerCase().replace(" ", "_") + ".png",
                        "ID" + (1000 + i),
                        1L,
                        1L,
                        1L,
                        1L,
                        new LoginDto(email, "123")
                );
                create(userDto, adminRole);
            }
        }
    }
}

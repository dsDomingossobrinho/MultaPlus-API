package ao.multaplus.auth.service;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.dtos.RegisterDto;
import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.repository.AuthRepository;
import ao.multaplus.auth.response.LoginResponse;
import ao.multaplus.role.service.RoleService;
import ao.multaplus.security.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService,UserDetailsService {

    
    private final AuthRepository authRepository;
    
    private final RoleService roleService;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    private final TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username);
    }

    @Override
    public Auth register(RegisterDto registerDto) {

        if (authRepository.findByEmail(registerDto.email()) != null) {
            return null;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerDto.password());

        Auth auth = new Auth();
        auth.setEmail(registerDto.email());
        auth.setPassword(encryptedPassword);
        auth.setRole(roleService.findByRole(registerDto.role()));
        return authRepository.save(auth);
    }

    @Override
    public LoginResponse login(LoginDto loginDto) {

        var usernamePassowrd = new UsernamePasswordAuthenticationToken(loginDto.email(), loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassowrd);

        return new LoginResponse(tokenService.generateToken((Auth) auth.getPrincipal()));
    }


}

package ao.multaplus.auth.service;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.dtos.RegisterDto;
import ao.multaplus.auth.dtos.VerifyLoginDto;
import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.response.LoginResponse;
import ao.multaplus.user.entity.Users;
import org.springframework.security.core.context.SecurityContextHolder;

public interface AuthService {

    Auth register(RegisterDto registerDto);
    void login(LoginDto loginDto, boolean sendOtpByEmail);
    LoginResponse login(LoginDto loginDto);
    LoginResponse validateLogin(VerifyLoginDto verifyLoginDto);
    default  Users currentUser() {
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return ((Auth) principal).getUsers();
        } catch (Exception e) {
            throw new SecurityException("Usuário não autenticado ou contexto inválido.", e);
        }
    }


}

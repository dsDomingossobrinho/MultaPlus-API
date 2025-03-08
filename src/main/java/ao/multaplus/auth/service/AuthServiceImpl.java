package ao.multaplus.auth.service;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.dtos.RegisterDto;
import ao.multaplus.auth.dtos.VerifyLoginDto;
import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.repository.AuthRepository;
import ao.multaplus.auth.response.LoginResponse;
import ao.multaplus.mail.MailServiceImpl;
import ao.multaplus.redis.RedisServiceImpl;
import ao.multaplus.role.service.RoleService;
import ao.multaplus.security.TokenService;
import ao.multaplus.utils.Utils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService, UserDetailsService {
    private final AuthRepository authRepository;

    private final RoleService roleService;
    private final MailServiceImpl mailService;
    private final RedisServiceImpl redisService;
    private final TokenService tokenService;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Value("${api.security.otp.expiration}")
    private long otpExpiration;
    @Override
    public UserDetails loadUserByUsername(
            String username) throws UsernameNotFoundException {
        return authRepository.findByEmail(username);
    }

    @Override
    @Transactional
    public Auth register(RegisterDto registerDto) {
        if (authRepository.findByEmail(registerDto.email()) != null) {
            return null;
        }
        String encryptedPassword = passwordEncoder.encode(registerDto.password());
        Auth auth = new Auth();
        auth.setEmail(registerDto.email());
        auth.setPassword(encryptedPassword);
        auth.setRole(roleService.findByRole(registerDto.role()));
        return authRepository.save(auth);
    }

    @Override
    public void login(LoginDto loginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(loginDto.email(),
                loginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        sendOTP((Auth) auth.getPrincipal());
    }

    @Override
    public LoginResponse validateLogin(VerifyLoginDto verifyLoginDto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(verifyLoginDto.email(),
                verifyLoginDto.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        verifyOTP(verifyLoginDto.email(),verifyLoginDto.code() );
         return new LoginResponse(tokenService.generateToken((Auth) auth.getPrincipal()));
    }

    private void sendOTP(Auth auth) {
        var otp = Utils.gernerteOTP();
        redisService.save(auth.getEmail(), passwordEncoder.encode(otp.toString()),
                otpExpiration);
        mailService.sendOTP(auth.getEmail(), otp, otpExpiration);
    }

    private void verifyOTP(String email, Integer code) {
        var otp = redisService.get(email).orElse(null);
        if (otp == null || !passwordEncoder.matches(code.toString(), otp)) {
            throw new RuntimeException("Invalid OTP");
        }
        redisService.delete(email);
    }
}

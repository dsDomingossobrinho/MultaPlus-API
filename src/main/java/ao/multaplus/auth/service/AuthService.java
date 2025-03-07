package ao.multaplus.auth.service;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.dtos.RegisterDto;
import ao.multaplus.auth.entity.Auth;
import ao.multaplus.auth.response.LoginResponse;

public interface AuthService {

    Auth register(RegisterDto registerDto);

    LoginResponse login(LoginDto loginDto);


}

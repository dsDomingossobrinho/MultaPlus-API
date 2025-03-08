package ao.multaplus.auth.controller;

import ao.multaplus.auth.dtos.LoginDto;
import ao.multaplus.auth.dtos.VerifyLoginDto;
import ao.multaplus.auth.response.LoginResponse;
import ao.multaplus.auth.service.AuthService;
import ao.multaplus.security.SecurityConfigurations;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Authentication", description = "Authentication Endpoints")
@SecurityRequirement(name= SecurityConfigurations.SECURITY)
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    @Operation(summary = "Login", description = "Login To get the OTP code")
    @ResponseStatus(HttpStatus.OK)
    public void login(@RequestBody @Valid LoginDto loginDto) {
         authService.login(loginDto);
    }

    @PostMapping("/login/validate")
    @Operation(summary = "Login", description = "validate login and get the token")
    public ResponseEntity<LoginResponse> verifyLogin(@RequestBody @Valid VerifyLoginDto verifyLoginDto ) {
        return new ResponseEntity<>(authService.validateLogin(verifyLoginDto), HttpStatus.OK);
    }

    @GetMapping
    public String index() {
        return "index";
    }
}

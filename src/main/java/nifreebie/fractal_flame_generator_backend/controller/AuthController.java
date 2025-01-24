package nifreebie.fractal_flame_generator_backend.controller;

import nifreebie.fractal_flame_generator_backend.dto.request.AuthRequest;
import nifreebie.fractal_flame_generator_backend.dto.response.AuthResponse;
import nifreebie.fractal_flame_generator_backend.dto.response.ErrorResponse;
import nifreebie.fractal_flame_generator_backend.exception.DuplicateUsernameException;
import nifreebie.fractal_flame_generator_backend.exception.IncorrectUsernameOrPasswordException;
import nifreebie.fractal_flame_generator_backend.service.AuthService;
import nifreebie.fractal_flame_generator_backend.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign_up")
    public ResponseEntity<?> signUp(@RequestBody AuthRequest authRequest){
        try{
            UUID uuid = authService.signUp(authRequest.getUsername(), authRequest.getPassword());
            String token = JwtUtil.generateToken(uuid);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch (DuplicateUsernameException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping("/sign_in")
    public ResponseEntity<?> signIn(@RequestBody AuthRequest authRequest){
        try{
            UUID uuid = authService.signIn(authRequest.getUsername(), authRequest.getPassword());
            String token = JwtUtil.generateToken(uuid);
            return ResponseEntity.ok(new AuthResponse(token));
        } catch(IncorrectUsernameOrPasswordException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorResponse(e.getMessage()));
        }
    }

}

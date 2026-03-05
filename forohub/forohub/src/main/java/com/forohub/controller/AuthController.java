package com.forohub.controller;

import com.forohub.dto.*;
import com.forohub.entity.Perfil;
import com.forohub.entity.Usuario;
import com.forohub.repository.PerfilRepository;
import com.forohub.repository.UsuarioRepository;
import com.forohub.security.JwtService;
import com.forohub.security.UsuarioDetailsService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioRepository usuarioRepository;
    private final PerfilRepository perfilRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioDetailsService usuarioDetailsService; // 🔥 FALTABA ESTO

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(
            @RequestBody LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getCorreoElectronico(),
                        request.getContrasena()
                )
        );

        UserDetails userDetails =
                usuarioDetailsService.loadUserByUsername(
                        request.getCorreoElectronico()
                );

        String token = jwtService.generateToken(userDetails);

        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterRequest request) {

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCorreoElectronico(request.getCorreoElectronico());
        usuario.setContrasena(
                passwordEncoder.encode(request.getContrasena())
        );

        Perfil perfil = perfilRepository
                .findByNombre("ROLE_USER")
                .orElseThrow();

        usuario.setPerfiles(Set.of(perfil));

        usuarioRepository.save(usuario);

        return ResponseEntity.ok("Usuario registrado correctamente");
    }
}
package com.forohub.security;

import com.forohub.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return usuarioRepository.findByCorreoElectronico(username)
                .map(UsuarioDetails::new)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Usuario no encontrado"));
    }
}
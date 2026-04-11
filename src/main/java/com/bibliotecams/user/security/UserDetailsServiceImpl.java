package com.bibliotecams.user.security;

import com.bibliotecams.user.entity.Usuario;
import com.bibliotecams.user.repository.UsuarioBaseRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioBaseRepository usuarioRepository;

    public UserDetailsServiceImpl(UsuarioBaseRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        return new User(
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true,
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().getNombre()))
        );
    }
}
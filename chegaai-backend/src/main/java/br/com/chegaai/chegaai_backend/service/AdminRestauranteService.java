/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.service;

import br.com.chegaai.chegaai_backend.entity.Restaurante;
import br.com.chegaai.chegaai_backend.entity.Usuario;
import br.com.chegaai.chegaai_backend.entity.enums.Papel;
import br.com.chegaai.chegaai_backend.repository.RestauranteRepository;
import br.com.chegaai.chegaai_backend.repository.UsuarioRepository;
import br.com.chegaai.chegaai_backend.web.controller.dto.UsuarioDTO;
import jakarta.transaction.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminRestauranteService(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UsuarioDTO criarUsuarioDonoRestaurante(UsuarioDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O usuário não pode ser nulo.");
        }

        if (dto.restauranteId() == null) {
            throw new IllegalArgumentException("O ID do restaurante não pode ser nulo.");
        }

        Restaurante restaurante = restauranteRepository.findById(dto.restauranteId())
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado."));

        Usuario usuario = dto.toEntity();
        usuario.setRestaurante(restaurante);
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        usuario.setPapel(Papel.ROLE_DONO_RESTAURANTE);
        usuarioRepository.save(usuario);

        return new UsuarioDTO(usuario);
    }
}

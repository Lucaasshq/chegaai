package br.com.chegaai.chegaai_backend.web.dto;

import br.com.chegaai.chegaai_backend.entity.Usuario;
import br.com.chegaai.chegaai_backend.entity.enums.Papel;

public record UsuarioResponseDTO(
        String nomeCompleto,
        String email,
        Papel papel,
        Long restauranteId
) {
    public UsuarioResponseDTO(Usuario usuario) {
        this(
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getPapel(),
                usuario.getRestaurante() != null ? usuario.getRestaurante().getId() : null
        );
    }
}

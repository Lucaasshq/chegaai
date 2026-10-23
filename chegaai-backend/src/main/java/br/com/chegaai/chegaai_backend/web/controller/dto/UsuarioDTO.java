package br.com.chegaai.chegaai_backend.web.controller.dto;

import br.com.chegaai.chegaai_backend.entity.Usuario;
import br.com.chegaai.chegaai_backend.entity.enums.Papel;
import jakarta.validation.constraints.*;

public record UsuarioDTO(
        @NotNull(message = "O ID do restaurante é obrigatório")
        Long restauranteId,
        @NotBlank(message = "O nome completo é obrigatório")
        String nomeCompleto,
        @NotBlank(message = "O e-mail é obrigatório")
        @Email(message = "Formato de e-mail inválido")
        String email,
        @NotBlank(message = "A senha é obrigatória")
        @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres")
        @Pattern(
                regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!_]).*$",
                message = "A senha deve conter pelo menos uma letra maiúscula, uma minúscula, um número e um caractere especial (@#$%^&+=!_)"
        )
        String senha,
        @NotNull(message = "O papel do usuário é obrigatório")
        Papel papel

) {

    public UsuarioDTO(Usuario usuario) {
        this(
                usuario.getRestaurante() != null ? usuario.getRestaurante().getId() : null,
                usuario.getNomeCompleto(),
                usuario.getEmail(),
                usuario.getSenha(),
                usuario.getPapel()
        );
    }

    public Usuario toEntity(){
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(this.nomeCompleto());
        usuario.setEmail(this.email());
        usuario.setSenha(this.senha());
        usuario.setPapel(this.papel());
        return usuario;
    }




}

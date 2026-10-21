package br.com.chegaai.chegaai_backend.web.controller.dto;

public record TokenResponseDTO(
        String token,
        String tipo,
        String email,
        String papel
) {
}

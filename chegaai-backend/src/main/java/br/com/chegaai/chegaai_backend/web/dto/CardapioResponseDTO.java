/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.web.dto;

import br.com.chegaai.chegaai_backend.entity.Cardapio;

import java.time.LocalDateTime;

public record CardapioResponseDTO(
        Long id,
        Long restauranteId,
        String nome,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
    public CardapioResponseDTO(Cardapio cardapio) {
        this(
                cardapio.getId(),
                cardapio.getRestaurante() != null ? cardapio.getRestaurante().getId() : null,
                cardapio.getNome(),
                cardapio.isAtivo(),
                cardapio.getDataCriacao(),
                cardapio.getDataAtualizacao()
        );
    }
}
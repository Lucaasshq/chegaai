package br.com.chegaai.chegaai_backend.web.dto;

import br.com.chegaai.chegaai_backend.entity.Cardapio;
import jakarta.validation.constraints.NotBlank;

public record CardapioRequestDTO(
        Long restauranteId,
        @NotBlank(message = "O nome do cardápio é obrigatório")
        String nome
) {


    public CardapioRequestDTO(Cardapio cardapio) {
        this(
                cardapio.getRestaurante() != null ? cardapio.getRestaurante().getId() : null,
                cardapio.getNome()
        );
    }


    public Cardapio toEntity() {
        Cardapio cardapio = new Cardapio();
        cardapio.setNome(this.nome);
        return cardapio;
    }
}

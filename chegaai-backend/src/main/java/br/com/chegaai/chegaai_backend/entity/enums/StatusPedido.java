package br.com.chegaai.chegaai_backend.entity.enums;

import lombok.Getter;

@Getter
public enum StatusPedido {
    AGUARDANDO_LOJA_ACEITAR("Aguardando loja aceitar"),
    EM_PREPARACAO("Em preparação"),
    EM_ROTA_DE_ENTREGA("Em rota de entrega"),
    ENTREGUE("Entregue"),
    CANCELADO("Cancelado");

    private final String descricao;

    StatusPedido(String descricao) {
        this.descricao = descricao;
    }
}

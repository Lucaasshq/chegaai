package br.com.chegaai.chegaai_backend.entity.enums;

import lombok.Getter;

@Getter
public enum MetodoPagamento {
        CARTAO_CREDITO("Cartão de Crédito"),
        CARTAO_DEBITO("Cartão de Débito"),
        DINHEIRO("Dinheiro"),
        PIX("Pix");

    private final String tipo;

    MetodoPagamento(String tipo) {
        this.tipo = tipo;
    }
}

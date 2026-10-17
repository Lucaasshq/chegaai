/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import br.com.chegaai.chegaai_backend.entity.abstracts.IdModel;
import br.com.chegaai.chegaai_backend.entity.enums.MetodoPagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "FORMA_PAGAMENTO")
@Getter
@Setter
public class FormaPagamento extends IdModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @NotNull(message = "A forma de pagamento deve estar associada a um restaurante")
    private Restaurante restaurante;

    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento", nullable = false, length = 50)
    @NotNull(message = "O método de pagamento é obrigatório")
    private MetodoPagamento metodoPagamento;


//  Atualizacoes Futuras para integração com o sistema de pagamento
//  private Long transacao_id;
//  private StatusPagamento statusPagamento;
}

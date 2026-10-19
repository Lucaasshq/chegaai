/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import br.com.chegaai.chegaai_backend.entity.abstracts.IdModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "ITEM_PEDIDO_COMPLEMENTO")
public class ItemPedidoComplemento extends IdModel {

    @ManyToOne
    @JoinColumn(name = "complemento_item_id", nullable = false)
    @NotNull(message = "O complemento item é obrigatório")
    private ComplementoItem complementoItem;

    @ManyToOne
    @JoinColumn(name = "item_pedido_id", nullable = false)
    @NotNull(message = "O item pedido é obrigatório")
    private ItemPedido itemPedido;

    @Column(name = "nome_congelado", nullable = false, length = 100)
    @NotBlank(message = "O nome congelado do complemento item é obrigatório")
    private String nomeCongelado;

    @Column(name = "preco_adicional_congelado", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O preço adicional congelado é obrigatório")
    private BigDecimal precoAdicionalCongelado;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}

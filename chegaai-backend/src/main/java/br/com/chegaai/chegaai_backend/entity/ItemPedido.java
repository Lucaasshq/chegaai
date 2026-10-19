/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import br.com.chegaai.chegaai_backend.entity.abstracts.IdModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "item_pedido")
@Getter
@Setter
public class ItemPedido extends IdModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "produto_id", nullable = false)
    @NotNull(message = "O item deve estar associado a um produto")
    private Produto produto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id", nullable = false)
    @NotNull(message = "O item deve estar associado a um pedido")
    private Pedido pedido;

    @Column(name = "quantidade", nullable = false)
    @NotNull(message = "A quantidade é obrigatória")
    private Integer quantidade;

    @Column(name = "nome_produto_congelado", nullable = false, length = 100)
    private String nomeProdutoCongelado;

    @Column(name = "preco_unitario_congelado", nullable = false, precision = 10, scale = 2)
    private BigDecimal precoUnitarioCongelado;

    @Column(name = "observacao_item", length = 255)
    private String observacaoItem;

    // Mapeamento bidirecional: avisa ao JPA que os complementos são mapeados pelo atributo 'itemPedido' em ItemPedidoComplemento
    @OneToMany(mappedBy = "itemPedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedidoComplemento> complementos;
}

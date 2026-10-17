/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import br.com.chegaai.chegaai_backend.entity.enums.StatusPedido;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "PEDIDO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @NotNull(message = "O pedido deve estar associado a um restaurante")
    private Restaurante restaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    @NotNull(message = "A forma de pagamento é obrigatória")
    private FormaPagamento formaPagamento;

    @Column(name = "codigo_pedido", nullable = false, unique = true, length = 20)
    @NotBlank(message = "O código do pedido é obrigatório")
    private String codigoPedido;

    // Dados congelados do cliente no momento da compra
    @Column(name = "cliente_nome", nullable = false, length = 100)
    @NotBlank(message = "O nome do cliente é obrigatório")
    private String clienteNome;

    @Column(name = "cliente_telefone", nullable = false, length = 20)
    @NotBlank(message = "O telefone do cliente é obrigatório")
    private String clienteTelefone;

    @Column(name = "cliente_endereco_completo", nullable = false, length = 255)
    @NotBlank(message = "O endereço de entrega é obrigatório")
    private String clienteEnderecoCompleto;

    @Column(name = "sub_total")
    private BigDecimal subTotal; // Atualizações futuras conforme o diagrama

    @Column(name = "valor_total", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O valor total é obrigatório")
    private BigDecimal valorTotal;

    @Column(name = "cupom_desconto")
    private String cupomDesconto; // Atualizações futuras

    @Column(name = "troco", precision = 10, scale = 2)
    private BigDecimal troco;

    @Column(name = "taxa_entrega", precision = 10, scale = 2)
    private BigDecimal taxaEntrega; // Atualizações futuras

    @Column(name = "status", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status do pedido é obrigatório")
    private StatusPedido statusPedido;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @Column(name = "data_pedido", nullable = false)
    private LocalDateTime dataPedido;

    // Relacionamento com os itens do pedido
    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itens = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataPedido = LocalDateTime.now();
    }
}
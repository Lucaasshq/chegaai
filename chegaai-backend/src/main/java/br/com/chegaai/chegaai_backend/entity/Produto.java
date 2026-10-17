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
@Table(name = "PRODUTO")
@Getter
@Setter
public class Produto extends IdModel {

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome do Produto é obrigatório")
    private String nome;

    @Column(name = "descricao", nullable = true, length = 255)
    private String descricao;

    @Column(name = "disponivel", nullable = false)
    private boolean disponivel = true;

    @Column(name = "preco", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O preço do Produto é obrigatório")
    private BigDecimal preco;

    @Column(name = "promocao", nullable = false)
    private boolean promocao = false;

    @Column(name = "preco_promocional", nullable = true, precision = 10, scale = 2)
    private BigDecimal precoPromocional;

    @Column(name = "foto_url", nullable = true, length = 255)
    private String fotoUrl;

    @Column(name = "ordem_exibicao", nullable = true)
    private Integer ordemExibicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "categoria_id", nullable = false)
    @NotNull(message = "O Produto deve estar associado a uma Categoria")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @NotNull(message = "O Produto deve estar associado a um Restaurante")
    private Restaurante restaurante;
}

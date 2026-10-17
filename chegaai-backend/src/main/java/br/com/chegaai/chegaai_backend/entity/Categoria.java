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

@Entity
@Getter
@Setter
@Table(name = "CATEGORIA")
public class Categoria extends IdModel {

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome da categoria é obrigatório")
    private String nome;

    @Column(name = "descricao", nullable = true, length = 255)
    private String descricao;

    @Column(name = "ordem_exibicao", nullable = true)
    private Integer ordemExibicao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cardapio_id", nullable = false)
    @NotNull(message = "A categoria deve estar associada a um cardápio")
    private Cardapio cardapio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = false)
    @NotNull(message = "A categoria deve estar associada a um restaurante")
    private Restaurante restaurante;

}

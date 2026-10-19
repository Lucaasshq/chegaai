/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import br.com.chegaai.chegaai_backend.entity.abstracts.IdModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "COMPLEMENTO_GRUPO")
public class ComplementoGrupo extends IdModel {

    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome Complemento Grupo é obrigatório")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Column(name = "min_escolhas", nullable = false)
    private Integer minEscolhas;

    @Column(name = "max_escolhas", nullable = false)
    private Integer maxEscolhas;
}

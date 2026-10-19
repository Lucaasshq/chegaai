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
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "COMPLEMENTO_ITEM")
@Getter
@Setter
public class ComplementoItem extends IdModel {

    @ManyToOne
    @JoinColumn(name = "complemento_grupo_id", nullable = false)
    private ComplementoGrupo complementoGrupo;

    @Column(name = "nome", nullable = false, length = 100)
    @NotNull(message = "O nome do complemento item é obrigatório")
    @NotBlank(message = "O nome do complemento item não pode estar em branco")
    @Size(min = 3, max = 100, message = "O nome deve ter entre 3 e 100 caracteres")
    private String nome;

    @Column(name = "preco_adicional", nullable = false, precision = 10, scale = 2)
    @NotNull(message = "O preço adicional é obrigatório")
    private BigDecimal precoAdicional;

    @Column(name = "disponivel", nullable = false)
    @NotNull(message = "A disponibilidade do complemento item é obrigatória")
    private boolean disponivel;
}

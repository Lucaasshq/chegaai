/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */
package br.com.chegaai.chegaai_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "CARDAPIO")
@Getter
@Setter
public class Cardapio extends IdModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id",  nullable = false)
    @NotNull(message = "O cardápio deve estar associado a um restaurante")
    private Restaurante restaurante;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome do cardápio é obrigatório")
    private String nome;

}

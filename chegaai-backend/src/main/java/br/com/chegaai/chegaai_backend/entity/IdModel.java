/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */
package br.com.chegaai.chegaai_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@MappedSuperclass // indica que a tabela é herdada e não é uma entidade
@Getter
@Setter
public abstract class IdModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "data_criacao")
    @NotNull
    private LocalDateTime dataCriacao;

    @Column(name = "ativo")
    @NotNull
    private boolean ativo = true;

    @Column(name = "data_atualizacao")
    private LocalDateTime dataAtualizacao;

    @Column(name = "data_exclusao")
    private LocalDateTime dataExclusao;

    @PrePersist
    public void prePersist(){
        this.dataCriacao = LocalDateTime.now();
        this.ativo = false;
    }

    @PreUpdate
    public void preUpdate(){
        this.dataAtualizacao = LocalDateTime.now();
    }
}

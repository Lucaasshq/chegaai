/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "CLIENTE")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @Column(name = "nome_completo", nullable = false, length = 100)
    @NotBlank(message = "O nome completo é obrigatório")
    private String nomeCompleto;

    @Column(name = "telefone", nullable = false, length = 20)
    @NotBlank(message = "O telefone é obrigatório")
    private String telefone;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "email", length = 100)
    private String email; // Atualizações futuras

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    // Relacionamento com os endereços do cliente
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Endereco> enderecos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}

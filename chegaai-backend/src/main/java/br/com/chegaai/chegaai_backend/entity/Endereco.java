/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "ENDERECO")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "endereco_id")
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @NotNull(message = "O endereço deve estar associado a um cliente")
    private Cliente cliente;

    @Column(name = "rua", nullable = false, length = 150)
    @NotBlank(message = "A rua é obrigatória")
    private String rua;

    @Column(name = "numero", nullable = false, length = 20)
    @NotBlank(message = "O número é obrigatório")
    private String numero;

    @Column(name = "cep", nullable = false, length = 10)
    @NotBlank(message = "O CEP é obrigatório")
    private String cep;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "ponto_referencia", length = 150)
    private String pontoReferencia;

    @Column(name = "latitude")
    private Double latitude; // Atualizações futuras para calcular a distância entre o endereço do cliente e o restaurante

    @Column(name = "longitude")
    private Double longitude; // Atualizações futuras para calcular a distância entre o endereço do cliente e o restaurante

    @Column(name = "data_criacao", nullable = false)
    private LocalDateTime dataCriacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }
}

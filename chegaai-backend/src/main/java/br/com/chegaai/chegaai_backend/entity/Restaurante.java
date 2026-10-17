/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */
package br.com.chegaai.chegaai_backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalTime;

@Entity
@Table(name = "RESTAURANTE")
@Getter
@Setter
public class Restaurante extends IdModel {

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome do restaurante é obrigatório")
    private String nome;

    @Column(name = "cnpj", nullable = false, unique = true, length = 30)
    @NotBlank(message = "O CNPJ é obrigatório")
    private String cnpj;

    @Column(name = "email", length = 150)
    @Email(message = "E-mail inválido")
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

    @Column(name = "logo_url", length = 255)
    private String logoUrl;

    @Column(name = "banner_url", length = 255)
    private String bannerUrl;

    @Column(name = "horario_abertura")
    private LocalTime horarioAbertura;

    @Column(name = "horario_fechamento")
    private LocalTime horarioFechamento;

    @Column(name = "tempo_medio_preparo_minutos")
    private Integer tempoMedioPreparoMinutos;

    @Column(name = "pedido_minimo", precision = 10, scale = 2)
    private BigDecimal pedidoMinimo;

    @Column(name = "subdominio", unique = true, length = 50)
    private String subdominio;

}

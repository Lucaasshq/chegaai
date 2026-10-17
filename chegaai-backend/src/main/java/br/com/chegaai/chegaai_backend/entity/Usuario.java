/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */
package br.com.chegaai.chegaai_backend.entity;

import br.com.chegaai.chegaai_backend.entity.abstracts.IdModel;
import br.com.chegaai.chegaai_backend.entity.enums.Papel;
import jakarta.persistence.*;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USUARIO")
@Getter
@Setter
public class Usuario extends IdModel {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurante_id", nullable = true)
    private Restaurante restaurante;

    @Column(name = "nome", nullable = false, length = 100)
    @NotBlank(message = "O nome é obrigatório")
    private String nome;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @Column(name = "senha", nullable = false)
    @NotBlank(message = "A senha é obrigatória")
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "papel", nullable = false, length = 30)
    private Papel papel;

    /**
     * Validação automática: Se não for ADMIN_GLOBAL, o restaurante_id é obrigatório.
     */
    @AssertTrue(message = "O restaurante é obrigatório para usuários que não são ADMIN_GLOBAL")
    public boolean isRestauranteObrigatorioConformePapel() {
       if (this.papel == Papel.ROLE_GLOBAL_ADMIN){
           return true;
       }
       return this.restaurante != null;
    }

    /**
     * Garantia de segurança no nível de persistência do Banco de Dados
     */

    @PrePersist
    @PreUpdate
    private void validarIntegridadeRestaurante(){
        if (this.papel != Papel.ROLE_GLOBAL_ADMIN && this.restaurante == null) {
            throw new IllegalStateException("O restaurante é obrigatório para usuários que não são ADMIN_GLOBAL");
        }
    }
}

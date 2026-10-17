package br.com.chegaai.chegaai_backend.entity.enums;

import lombok.Getter;

@Getter
public enum Papel {
    ROLE_GLOBAL_ADMIN("Role_global_admin"),
    ROLE_DONO_RESTAURANTE("Role_dono_restaurante"),
    ROLE_GERENTE_RESTAURANTE("Role_gerente_restaurante"),
    ROLE_COZINHA_RESTAURANTE("Role_cozinha_restaurante"),
    ROLE_FUNCIONARIO_RESTAURANTE("Role_funcionario_restaurante");

    private final String autoridade;

    Papel(String autoridade) {
        this.autoridade = autoridade;
    }
}

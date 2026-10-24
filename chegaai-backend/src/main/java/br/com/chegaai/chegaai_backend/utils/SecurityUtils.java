/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.utils;

import br.com.chegaai.chegaai_backend.entity.Usuario;
import br.com.chegaai.chegaai_backend.entity.enums.Papel;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.springframework.security.access.AccessDeniedException;

@Component
public class SecurityUtils {

    public static Usuario getUsuarioLogado(){
        return (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    public static Long getRestauranteIdUsuarioLogado(){
        Usuario usuarioLogado = getUsuarioLogado();
        if (usuarioLogado.getRestaurante() != null) {
            return usuarioLogado.getRestaurante().getId();
        }

        if (usuarioLogado.getRestaurante().getId() == null || usuarioLogado.getPapel() == Papel.ROLE_GLOBAL_ADMIN) {
            return null;
        }

            return null;
    }

    public static void validarAcessoTenant(Long restauranteId) throws AccessDeniedException {
        Usuario usuarioLogado = getUsuarioLogado();

        if (usuarioLogado.getPapel() == Papel.ROLE_GLOBAL_ADMIN){
            return;
        }

        Long restauranteIdDoUsuario = getRestauranteIdUsuarioLogado();

        if (restauranteIdDoUsuario == null || !restauranteIdDoUsuario.equals(restauranteId)){
            throw new AccessDeniedException("Acesso negado: Você não tem permissão para gerenciar dados deste restaurante.");
        }
    }
}

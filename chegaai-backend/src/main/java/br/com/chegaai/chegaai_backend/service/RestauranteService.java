/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.service;

import br.com.chegaai.chegaai_backend.entity.Restaurante;
import br.com.chegaai.chegaai_backend.repository.RestauranteRepository;
import br.com.chegaai.chegaai_backend.repository.UsuarioRepository;
import br.com.chegaai.chegaai_backend.utils.SecurityUtils;
import br.com.chegaai.chegaai_backend.web.dto.RestauranteRequestDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import org.springframework.security.access.AccessDeniedException;

@Service
public class RestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final UsuarioRepository usuarioRepository;

    public RestauranteService(RestauranteRepository restauranteRepository, UsuarioRepository usuarioRepository) {
        this.restauranteRepository = restauranteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public Restaurante criar(RestauranteRequestDTO dto) {
        if (dto == null) {
            throw new IllegalArgumentException("O restaurante não pode ser nulo.");
        }
        return restauranteRepository.save(dto.toEntity());
    }

    @Transactional
    public Restaurante atualizar(Long id, RestauranteRequestDTO dto) throws AccessDeniedException {
        if (dto == null || id == null){
            throw new IllegalArgumentException("O restaurante ou ID não pode ser nulo.");
        }

        SecurityUtils.validarAcessoTenant(id);

        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado."));

        restaurante.atualizarInformacoes(dto);

        return restauranteRepository.save(restaurante);
    }


    @Transactional
    public void desativar(Long id) throws AccessDeniedException {
        if (id == null){
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        SecurityUtils.validarAcessoTenant(id);

        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado."));

        restaurante.setAtivo(false);
        restauranteRepository.save(restaurante);
    }

    public Restaurante buscarPorId(Long id) throws AccessDeniedException {
        if (id == null){
            throw new IllegalArgumentException("O ID não pode ser nulo.");
        }

        SecurityUtils.validarAcessoTenant(id);

        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado."));
        return  restaurante;
    }


}

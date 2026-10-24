/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.service;

import br.com.chegaai.chegaai_backend.entity.Cardapio;
import br.com.chegaai.chegaai_backend.entity.Restaurante;
import br.com.chegaai.chegaai_backend.entity.Usuario;
import br.com.chegaai.chegaai_backend.entity.enums.Papel;
import br.com.chegaai.chegaai_backend.repository.CardapioRepository;
import br.com.chegaai.chegaai_backend.utils.SecurityUtils;
import br.com.chegaai.chegaai_backend.web.dto.CardapioRequestDTO;
import org.springframework.stereotype.Service;

@Service
public class CardapioService {

    private final CardapioRepository cardapioRepository;
    private final RestauranteService restauranteService;

    public CardapioService(CardapioRepository cardapioRepository, RestauranteService restauranteService) {
        this.cardapioRepository = cardapioRepository;
        this.restauranteService = restauranteService;
    }


    public Cardapio criarCardapio(CardapioRequestDTO dto){
        if (dto == null) {
            throw new IllegalArgumentException("O DTO do cardápio é obrigatório");
        }

        Usuario usuario = SecurityUtils.getUsuarioLogado();
        Long restauranteIdAlvo;

        if (usuario.getPapel() == Papel.ROLE_GLOBAL_ADMIN) {
            if (dto.restauranteId() == null) {
                throw new IllegalArgumentException("O Administrador Global deve informar o ID do restaurante.");
            }
            restauranteIdAlvo = dto.restauranteId();
        } else {
            // Se for Dono de Restaurante, pegamos direto do token/usuário logado
            restauranteIdAlvo = SecurityUtils.getRestauranteIdUsuarioLogado();
            if (restauranteIdAlvo == null) {
                throw new IllegalArgumentException("O usuário logado não está associado a nenhum restaurante.");
            }
        }

        Restaurante restaurante = restauranteService.buscarPorId(restauranteIdAlvo);
        Cardapio cardapio = dto.toEntity();
        cardapio.setRestaurante(restaurante);

        return cardapioRepository.save(cardapio);
    }

    public void desativar(Long cardapioId) {
        if (cardapioId == null) {
            throw new IllegalArgumentException("O ID do cardápio é obrigatório");
        }

        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new IllegalArgumentException("Cardápio não encontrado."));

        SecurityUtils.validarAcessoTenant(cardapio.getRestaurante().getId());

        cardapio.setAtivo(false);
        cardapioRepository.save(cardapio);
    }


    public Cardapio buscarPorId(Long cardapioId) {
        if (cardapioId == null) {
            throw new IllegalArgumentException("O ID do cardápio é obrigatório");
        }

        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new IllegalArgumentException("Cardápio não encontrado."));


        SecurityUtils.validarAcessoTenant(cardapio.getRestaurante().getId());

        return cardapio;
    }


    public Cardapio atualizar(Long cardapioId, CardapioRequestDTO dto) {
        if (cardapioId == null) {
            throw new IllegalArgumentException("O ID do cardápio é obrigatório");
        }
        if (dto.nome() == null || dto.nome().isBlank()) {
            throw new IllegalArgumentException("O nome do cardápio é obrigatório");
        }

        Cardapio cardapio = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new IllegalArgumentException("Cardápio não encontrado."));

        SecurityUtils.validarAcessoTenant(cardapio.getRestaurante().getId());

        cardapio.setNome(dto.nome());
        return cardapioRepository.save(cardapio);
    }
}

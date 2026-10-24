/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.web.controller;

import br.com.chegaai.chegaai_backend.entity.Cardapio;
import br.com.chegaai.chegaai_backend.service.CardapioService;
import br.com.chegaai.chegaai_backend.web.dto.CardapioRequestDTO;
import br.com.chegaai.chegaai_backend.web.dto.CardapioResponseDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/gerencia/cardapio")
public class CardapioController {

    private CardapioService cardapioService;

    public CardapioController(CardapioService cardapioService) {
        this.cardapioService = cardapioService;
    }

    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @PostMapping
    public ResponseEntity<CardapioResponseDTO> criarCardapio(@RequestBody @Valid CardapioRequestDTO dto) {
        Cardapio cardapio = cardapioService.criarCardapio(dto);
        return ResponseEntity.ok(new CardapioResponseDTO(cardapio));
    }

    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @PutMapping("/{cardapioId}")
    public ResponseEntity<CardapioResponseDTO> atualizarCardapio(@PathVariable Long cardapioId, @RequestBody @Valid CardapioRequestDTO cardapioRequestDTO) {
        Cardapio cardapio = cardapioService.atualizar(cardapioId, cardapioRequestDTO);
        return ResponseEntity.ok(new CardapioResponseDTO(cardapio));
    }

    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @PutMapping("/{cardapioId}/desativar")
    public ResponseEntity<Void> desativarCardapio(@PathVariable Long cardapioId) throws AccessDeniedException {
        cardapioService.desativar(cardapioId);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @GetMapping("/{cardapioId}")
    public ResponseEntity<CardapioResponseDTO> buscarCardapio(@PathVariable Long cardapioId) {
        Cardapio cardapio = cardapioService.buscarPorId(cardapioId);
        return ResponseEntity.ok(new CardapioResponseDTO(cardapio));
    }
}

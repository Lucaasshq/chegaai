/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.web.controller;

import br.com.chegaai.chegaai_backend.entity.Restaurante;
import br.com.chegaai.chegaai_backend.service.RestauranteService;
import br.com.chegaai.chegaai_backend.web.controller.dto.RestauranteDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;

@RestController
@RequestMapping("/api/gerencia/restaurante")
@Tag(name = "Gerência Restaurante", description = "Endpoints para o dono do restaurante gerenciar seu próprio negócio")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }


    @Operation(summary = "Atualizar Restaurante")
    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @PutMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDTO> atualizarRestaurante(@PathVariable Long restauranteId, @RequestBody @Valid RestauranteDTO dto) throws AccessDeniedException {
        RestauranteDTO restaurante = restauranteService.atualizar(restauranteId, dto);
        return ResponseEntity.ok(restaurante);
    }


    @Operation(summary = "Desativar Restaurante")
    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @PutMapping("/{restauranteId}/desativar")
    public ResponseEntity<Void> desativarRestaurante(@PathVariable Long restauranteId) throws AccessDeniedException {
        restauranteService.desativar(restauranteId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar Restaurante")
    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN') or hasRole('ROLE_DONO_RESTAURANTE')")
    @GetMapping("/{restauranteId}")
    public ResponseEntity<RestauranteDTO> buscar(@PathVariable Long restauranteId) throws AccessDeniedException {
        RestauranteDTO restauranteDTO = restauranteService.buscarPorId(restauranteId);
        return ResponseEntity.ok().body(restauranteDTO);
    }

}

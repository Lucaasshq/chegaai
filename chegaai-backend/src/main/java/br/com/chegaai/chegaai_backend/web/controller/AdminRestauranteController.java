/*
 * Copyright (c) 2026 ChegaAI. Todos os direitos reservados.
 * Este software é proprietário da ChegaAI.
 * A cópia, distribuição ou uso não autorizado é estritamente proibido.
 */

package br.com.chegaai.chegaai_backend.web.controller;

import br.com.chegaai.chegaai_backend.entity.Restaurante;
import br.com.chegaai.chegaai_backend.service.AdminRestauranteService;
import br.com.chegaai.chegaai_backend.service.RestauranteService;
import br.com.chegaai.chegaai_backend.web.controller.dto.RestauranteDTO;
import br.com.chegaai.chegaai_backend.web.controller.dto.UsuarioDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/restaurante")
@Tag(name = "Admin Restaurante", description = "Endpoints exclusivos para administradores globais")
public class AdminRestauranteController {

    private final AdminRestauranteService adminRestauranteService;
    private final RestauranteService restauranteService;

    public AdminRestauranteController(AdminRestauranteService adminRestauranteService, RestauranteService restauranteService) {
        this.adminRestauranteService = adminRestauranteService;
        this.restauranteService = restauranteService;
    }

    @Operation(summary = "Criar Restaurante", description = "Cria um novo restaurante no sistema. Requer permissão de administrador global.")
    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN')")
    @PostMapping
    public ResponseEntity<RestauranteDTO> criarRestaurante(@RequestBody @Valid RestauranteDTO dto){
        RestauranteDTO restauranteDTO = restauranteService.criar(dto);
        return ResponseEntity.status(201).body(restauranteDTO);
    }

    @Operation(summary = "Criar Usuário Dono de Restaurante", description = "Cria um novo usuário dono de restaurante. Requer permissão de administrador global.")
    @PreAuthorize("hasRole('ROLE_GLOBAL_ADMIN')")
    @PostMapping("/usuario")
    public ResponseEntity<UsuarioDTO> criarUsuarioDonoRestaurante(@RequestBody @Valid UsuarioDTO dto) {
        UsuarioDTO usuario = adminRestauranteService.criarUsuarioDonoRestaurante(dto);
        return ResponseEntity.status(201).body(usuario);
    }



}

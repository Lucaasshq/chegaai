package br.com.chegaai.chegaai_backend.web.dto;

import br.com.chegaai.chegaai_backend.entity.Restaurante;

import java.time.LocalDateTime;

public record RestauranteResponseDTO(
        String nome,
        String cnpj,
        String telefone,
        String logoUrl,
        String bannerUrl,
        String subdominio,
        boolean ativo,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao

) {

    public RestauranteResponseDTO(Restaurante restaurante) {
        this(
                restaurante.getNome(),
                restaurante.getCnpj(),
                restaurante.getTelefone(),
                restaurante.getLogoUrl(),
                restaurante.getBannerUrl(),
                restaurante.getSubdominio(),
                restaurante.isAtivo(),
                restaurante.getDataCriacao(),
                restaurante.getDataAtualizacao()
        );
    }
}

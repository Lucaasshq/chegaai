package br.com.chegaai.chegaai_backend.web.controller.dto;

import br.com.chegaai.chegaai_backend.entity.Restaurante;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import java.math.BigDecimal;
import java.time.LocalTime;

public record RestauranteDTO(
        @NotBlank(message = "O nome do restaurante é obrigatório")
        String nome,
        @CNPJ(message = "CNPJ inválido")
        @NotBlank(message = "O CNPJ é obrigatório")
        String cnpj,
        @NotBlank(message = "O email é obrigatório")
        @Email(message = "E-mail inválido")
        String email,

        @NotBlank(message = "O telefone é obrigatório")
        String telefone,
        String logoUrl,
        String bannerUrl,
        LocalTime horarioAbertura,
        LocalTime horarioFechamento,
        Integer tempoMedioPreparoMinutos,
        BigDecimal pedidoMinimo,
        String subdominio
) {

    public RestauranteDTO(Restaurante restaurante) {
        this(
                restaurante.getNome(),
                restaurante.getCnpj(),
                restaurante.getEmail(),
                restaurante.getTelefone(),
                restaurante.getLogoUrl(),
                restaurante.getBannerUrl(),
                restaurante.getHorarioAbertura(),
                restaurante.getHorarioFechamento(),
                restaurante.getTempoMedioPreparoMinutos(),
                restaurante.getPedidoMinimo(),
                restaurante.getSubdominio()
        );
    }
    public Restaurante toEntity(){
        Restaurante restaurante = new Restaurante();
        restaurante.setNome(this.nome);
        restaurante.setCnpj(this.cnpj);
        restaurante.setEmail(this.email);
        restaurante.setTelefone(this.telefone);
        restaurante.setLogoUrl(this.logoUrl);
        restaurante.setBannerUrl(this.bannerUrl);
        restaurante.setHorarioAbertura(this.horarioAbertura);
        restaurante.setHorarioFechamento(this.horarioFechamento);
        restaurante.setTempoMedioPreparoMinutos(this.tempoMedioPreparoMinutos);
        restaurante.setPedidoMinimo(this.pedidoMinimo);
        restaurante.setSubdominio(this.subdominio);
        return restaurante;
    }
}

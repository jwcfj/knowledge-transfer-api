package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Processo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessoDTOv2(
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String descricao,
        @NotNull @NotBlank
        String ferramentas,
        @NotNull @NotBlank
        String indicadores,
        @NotNull @NotBlank
        String metricas,
        @NotNull @NotBlank
        String stakeholders,
        @NotNull @NotBlank
        String etapas
) {}

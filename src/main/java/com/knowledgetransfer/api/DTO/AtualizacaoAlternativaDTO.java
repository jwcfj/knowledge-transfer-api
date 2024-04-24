package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlternativaDTO(
        @NotNull
        Long alternativa_id,
        @NotNull @NotBlank
        String pergunta
) {
}

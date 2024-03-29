package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlternativaDTO(
        @NotNull
        Long id,
        String pergunta,
        String processo
) {
}

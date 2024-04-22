package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlternativaDTO(
        @NotNull
        Long id,
        @NotNull
        String pergunta
        //,
//        String processo
) {
}

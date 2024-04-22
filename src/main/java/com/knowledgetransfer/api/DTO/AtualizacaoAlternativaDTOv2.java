package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlternativaDTOv2(
        @NotNull
        Long id,
        @NotNull @NotBlank
        String pergunta
) {
}

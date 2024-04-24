package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AlternativaCadastradaDTO(
        @NotNull
        Long alternativa_id,
        @NotNull
        String pergunta
) {
}

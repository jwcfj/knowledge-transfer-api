package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AlternativaDTO(
        @NotNull @NotBlank
        String pergunta//alternativa,
        //,
//        @NotNull
//        String processos
) {
}

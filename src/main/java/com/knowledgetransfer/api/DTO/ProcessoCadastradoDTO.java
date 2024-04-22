package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ProcessoCadastradoDTO(
        @NotNull
        Long id,
        @NotNull @NotBlank
        String nome,
        @NotNull @NotBlank
        String descricao
        //,
//        @NotNull
//        String processos
) {
}

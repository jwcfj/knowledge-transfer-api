package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record ProcessoPossuidoCadastradoDTO(
        Long possui_id,
//        @NotNull
//        Long alternativa_id,
        @NotNull
        Long processo_id,
        @NotNull
        String nome_processo
) {
}

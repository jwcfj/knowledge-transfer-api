package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record ProcessoPossuidoDTO(
        @NotNull
        Long alternativa_id,

        @NotNull
        Long processo_id
) {
}

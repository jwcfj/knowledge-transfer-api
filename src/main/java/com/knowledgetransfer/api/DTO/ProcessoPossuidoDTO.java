package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProcessoPossuidoDTO(
        @NotNull @PositiveOrZero
        Long alternativa_id,

        @NotNull @PositiveOrZero
        Long processo_id
) {
}
package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record AtualizacaoProcessoPossuidoDTO(
        @NotNull @PositiveOrZero
        Long possui_id,
        @NotNull @PositiveOrZero
        Long alternativa_id,
        @NotNull @PositiveOrZero
        Long processo_id
        //,
//        String processo
) {
}

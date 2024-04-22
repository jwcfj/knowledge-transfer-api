package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;


public record AtualizacaoProcessoDTO(
        @NotNull @PositiveOrZero
        Long id,
        String nome,
        String descricao
){
}

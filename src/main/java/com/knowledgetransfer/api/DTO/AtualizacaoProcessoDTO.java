package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;


public record AtualizacaoProcessoDTO(
        @NotNull
        Long id,
        String nome,
        String descricao
){
}

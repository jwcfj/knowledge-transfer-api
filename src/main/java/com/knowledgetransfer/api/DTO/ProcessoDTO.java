package com.knowledgetransfer.api.DTO;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record ProcessoDTO(
        @NotBlank
        String nome,
        @NotNull
        String descricao
) {
}
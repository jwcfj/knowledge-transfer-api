package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RespostaDeFormularioDTO(
        @NotBlank
        String nome,
        @NotNull
        Long stakeholder_id,
        @NotNull
        Long formulario_id,
        @NotNull
        Long processo_id
) {
}

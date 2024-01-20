package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoAlternativaDTO(
        @NotNull
        Long id,
        String alternativa,
        AtualizacaoFormularioProcessoDTO processo
) {
}

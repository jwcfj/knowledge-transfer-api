package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtualizacaoQuestaoDTO(
        @NotNull
        Long id,
        String pergunta,
        List<AtualizacaoAlternativaDTO> alternativas
) {
}

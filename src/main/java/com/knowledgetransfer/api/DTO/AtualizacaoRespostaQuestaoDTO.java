package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

public record AtualizacaoRespostaQuestaoDTO(
        @NotNull
        Long id,
        Long alternativa_id
) {
}

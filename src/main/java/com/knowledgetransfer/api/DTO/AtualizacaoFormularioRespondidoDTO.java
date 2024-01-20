package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AtualizacaoFormularioRespondidoDTO(
        @NotNull
        Long id,
        List<AtualizacaoRespostaQuestaoDTO> respostasQuestoes
) {
}

package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;
import java.util.List;

public record FormularioRespondidoDTO(
        //@NotBlank
        //Timestamp data_criacao,
        @NotNull
        Long stakeholder_id,
        @NotNull
        Long formulario_id,
        @NotNull
        Long processo_id,
        @NotNull
        List<RespostaQuestaoDTO> respostasQuestoes
) {
}

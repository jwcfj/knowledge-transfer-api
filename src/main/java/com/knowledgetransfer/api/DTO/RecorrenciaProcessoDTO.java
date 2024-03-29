package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.RecorrenciaProcesso;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RecorrenciaProcessoDTO(
        @NotBlank
        String nome,
        @NotNull
        String descricao,

        @NotNull
        Long recorrencia
) {
    public RecorrenciaProcessoDTO(RecorrenciaProcesso recorrenciaProcesso){
        this(recorrenciaProcesso.getProcesso_nome(),recorrenciaProcesso.getDescricao(),recorrenciaProcesso.getRecorrencia());
    }
}

package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Recorrencia;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record RecorrenciaDTO(
        @NotNull
        Long total,
        @NotNull
        List<RecorrenciaProcessoDTO> recorrencia_processos
) {
    public RecorrenciaDTO(Recorrencia recorrencia){
        this(recorrencia.getTotal(),recorrencia.getRecorrencia_processos());
    }
}

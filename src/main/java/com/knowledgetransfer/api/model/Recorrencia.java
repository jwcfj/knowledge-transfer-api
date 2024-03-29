package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.RecorrenciaProcessoDTO;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class Recorrencia {

    private Long total;
    private List<RecorrenciaProcessoDTO> recorrencia_processos;

    public Recorrencia(Long total, List<RecorrenciaProcessoDTO> recorrencia_processos) {
        this.total = total;
        this.recorrencia_processos = recorrencia_processos;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<RecorrenciaProcessoDTO> getRecorrencia_processos() {
        return recorrencia_processos;
    }

    public void setRecorrencia_processos(List<RecorrenciaProcessoDTO> recorrencia_processos) {
        this.recorrencia_processos = recorrencia_processos;
    }
}

package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Processo;

public record ListagemProcessoDTO(
        Long id,
        String nome,
        String descricao

) {
    public ListagemProcessoDTO(Processo processo){
        this(processo.getId(),processo.getNome(),processo.getDescricao());
    }
}

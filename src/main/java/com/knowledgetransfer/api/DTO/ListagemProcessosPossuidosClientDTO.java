package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Possui;

//telas procesos da alternativa
public record ListagemProcessosPossuidosClientDTO(
        Long id,
        Long processo_id,
        String processo
) {
    public ListagemProcessosPossuidosClientDTO(Possui possui){
        this(possui.getId(), possui.getProcesso().getId(), possui.getProcesso().getNome());
    }

}

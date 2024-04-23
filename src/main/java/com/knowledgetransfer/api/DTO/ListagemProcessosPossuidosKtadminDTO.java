package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Possui;
//telas procesos da alternativa
public record ListagemProcessosPossuidosKtadminDTO(
        Long possui_id,
        Long processo_id,
        String nome_processo
) {
    public ListagemProcessosPossuidosKtadminDTO(Possui possui){
        this(possui.getId(), possui.getProcesso().getId(), possui.getProcesso().getNome());
    }

}
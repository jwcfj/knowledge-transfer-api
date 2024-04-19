package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;

public record ListagemAlternativaDTO(
        Long id,
        //String alternativa,
        String pergunta,
        Long processo_id,
        String processo
) {
    public ListagemAlternativaDTO(Alternativa alternativa){
        this(alternativa.getId(), alternativa.getPergunta()/*alternativa.getAlternativa()*/,alternativa.getProcesso().getId(),alternativa.getProcesso().getNome());
    }

}

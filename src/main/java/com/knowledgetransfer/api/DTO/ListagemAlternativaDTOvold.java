package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;

public record ListagemAlternativaDTOvold(
        Long id,
        //String alternativa,
        String pergunta,
        Long processo_id,
        String processo
) {
    public ListagemAlternativaDTOvold(Alternativa alternativa){
        this(alternativa.getId(), alternativa.getPergunta(),1L,"a");
        //this(alternativa.getId(), alternativa.getPergunta()/*alternativa.getAlternativa()*/,alternativa.getProcesso().getId(),alternativa.getProcesso().getNome());
    }

}

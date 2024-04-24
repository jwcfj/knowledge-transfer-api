package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;

public record ListagemAlternativaClientDTO(

        Long alternativa_id,
        String pergunta
) {
    public ListagemAlternativaClientDTO(Alternativa alternativa){
        this( alternativa.getId(), alternativa.getPergunta());
    }
}

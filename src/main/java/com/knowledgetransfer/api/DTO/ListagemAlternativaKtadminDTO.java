package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;


public record ListagemAlternativaKtadminDTO(

        Long alternativa_id,
        String pergunta
) {
    public ListagemAlternativaKtadminDTO(Alternativa alternativa){

        this( alternativa.getId(), alternativa.getPergunta());
    }
}

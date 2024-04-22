package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;

//tela alternativas
public record ListagemAlternativaKtAdminDTO(

        Long id,
        String pergunta
) {
    public ListagemAlternativaKtAdminDTO(Alternativa alternativa){
        this( alternativa.getId(), alternativa.getPergunta());
    }
}

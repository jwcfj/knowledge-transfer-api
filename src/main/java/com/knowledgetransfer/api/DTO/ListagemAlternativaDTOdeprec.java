package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Possui;

//tela alternativas
public record ListagemAlternativaDTOdeprec(

        Long alternativa_id,
        String pergunta
) {
    public ListagemAlternativaDTOdeprec(Possui possui){
        this( possui.getAlternativa().getId(), possui.getAlternativa().getPergunta());
    }
}

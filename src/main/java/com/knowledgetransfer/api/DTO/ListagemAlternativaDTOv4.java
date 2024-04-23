package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;

//tela alternativas
public record ListagemAlternativaDTOv4(

        Long alternativa_id,
        String pergunta
) {
    public ListagemAlternativaDTOv4(Alternativa alternativa){
        this( alternativa.getId(), alternativa.getPergunta());
    }
}

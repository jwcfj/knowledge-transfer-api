package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;

//tela alternativas
public record ListagemAlternativaDTOv3(

        Long alternativa_id,
        String pergunta
) {
    public ListagemAlternativaDTOv3(Possui possui){
        this( possui.getAlternativa().getId(), possui.getAlternativa().getPergunta());
    }
}

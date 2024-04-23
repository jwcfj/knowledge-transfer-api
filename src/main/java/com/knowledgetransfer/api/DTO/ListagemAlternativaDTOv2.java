package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;

import java.util.List;
import java.util.stream.Collectors;

//tela alternativas
public record ListagemAlternativaDTOv2(

        Long alternativa_id,
        String pergunta
) {
    public ListagemAlternativaDTOv2(Alternativa alternativa){
        this( alternativa.getId(), alternativa.getPergunta());
    }
}

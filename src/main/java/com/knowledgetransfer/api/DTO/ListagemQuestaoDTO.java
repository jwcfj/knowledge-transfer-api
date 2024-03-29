package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Formulario;
import com.knowledgetransfer.api.model.Questao;

import java.util.List;
import java.util.stream.Collectors;

public record ListagemQuestaoDTO(
        Long id,
        String pergunta//,
//        List<ListagemAlternativaDTO> alternativas
) {
    public ListagemQuestaoDTO(Questao questao){
        this( questao.getId(),questao.getPergunta() );//, mapAlternativa(questao.getAlternativas()));
    }
//
//    private static List<ListagemAlternativaDTO> mapAlternativa(List<Alternativa> alternativas) {
//        return alternativas.stream().map(ListagemAlternativaDTO::new).collect(Collectors.toList());
//    }

}

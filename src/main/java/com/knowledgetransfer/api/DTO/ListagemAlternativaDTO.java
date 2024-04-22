package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Possui;

import java.util.List;
import java.util.stream.Collectors;

//tela alternativas
public record ListagemAlternativaDTO(

        Long id,
        String pergunta,

        List<ListagemProcessosPossuidosClientDTO> processosPossuidos
) {
    public ListagemAlternativaDTO(Alternativa alternativa){
        this( alternativa.getId(), alternativa.getPergunta(),mapprocessosPossuidos(alternativa.getRelacionamento_possui()) );
    }
    private static List<ListagemProcessosPossuidosClientDTO> mapprocessosPossuidos(List<Possui> processosPossuidos) {
        return processosPossuidos.stream().map(ListagemProcessosPossuidosClientDTO::new).collect(Collectors.toList());
    }
}

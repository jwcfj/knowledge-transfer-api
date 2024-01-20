package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.RespostaQuestao;

import java.util.List;

public record ListagemRespostaQuestaoDTO(
        Long id,
        String pergunta,
        Long alternativa_id
) {
    public ListagemRespostaQuestaoDTO(RespostaQuestao respostaQuestao){
        this(respostaQuestao.getId(),respostaQuestao.getQuestao().getPergunta(),respostaQuestao.getAlternativa().getId());

    }
}

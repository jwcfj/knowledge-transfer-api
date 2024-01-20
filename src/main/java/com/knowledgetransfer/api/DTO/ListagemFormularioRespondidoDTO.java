package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.FormularioRespondido;
import com.knowledgetransfer.api.model.Questao;
import com.knowledgetransfer.api.model.RespostaQuestao;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

public record ListagemFormularioRespondidoDTO(
        Long id,
        Timestamp data_criacao,
        String nomeFormulario,
        String nomeStakeholder,
        List<ListagemRespostaQuestaoDTO>  respostas_questoes
) {
    public ListagemFormularioRespondidoDTO(FormularioRespondido formularioRespondido){
        this(formularioRespondido.getId(),formularioRespondido.getData_criacao(),formularioRespondido.getFormulario().getNome(),formularioRespondido.getStakeholder().getNome(),mapRespostasQuestoes(formularioRespondido.getRespostasQuestoes()) );
    }
    private static List<ListagemRespostaQuestaoDTO> mapRespostasQuestoes(List<RespostaQuestao> respostaQuestoes) {
        return respostaQuestoes.stream().map(ListagemRespostaQuestaoDTO::new).collect(Collectors.toList());
    }
}

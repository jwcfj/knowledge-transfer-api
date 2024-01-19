package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.FormularioRespondido;

import java.sql.Timestamp;

public record ListagemFormularioRespondidoDTO(
        Long id,
        Timestamp data_criacao,
        String nomeFormulario,
        String nomeStakeholder
) {
    public ListagemFormularioRespondidoDTO(FormularioRespondido formularioRespondido){
        this(formularioRespondido.getId(),formularioRespondido.getData_criacao(),formularioRespondido.getFormulario().getNome(),formularioRespondido.getStakeholder().getNome());
    }
}

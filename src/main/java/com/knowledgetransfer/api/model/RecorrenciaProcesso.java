package com.knowledgetransfer.api.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RecorrenciaProcesso {
    private Long id;
    private Long recorrencia;

    private String processo_nome;

    private String descricao;

    public RecorrenciaProcesso(Long id, Long recorrencia) {
        this.id = id;
        this.recorrencia = recorrencia;
        //this.processo_nome=processo_nome;
        //this.descricao=descricao;
    }

//    public RecorrenciaProcesso(Long id, Long recorrencia, String processo_nome, String descricao) {
//        this.id = id;
//        this.recorrencia = recorrencia;
//        this.processo_nome=processo_nome;
//        this.descricao=descricao;
//    }
    public RecorrenciaProcesso() {
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProcesso_nome() {
        return processo_nome;
    }

    public void setProcesso_nome(String processo_nome) {
        this.processo_nome = processo_nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecorrencia() {
        return recorrencia;
    }

    public void setRecorrencia(Long recorrencia) {
        this.recorrencia = recorrencia;
    }
    public void setRecorrenciaPlus1() {
        this.recorrencia++;
    }
}

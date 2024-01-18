package com.knowledgetransfer.api.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class Etapa {
    private String nome_etapa;
    private String artefato;
    private String instrumento;
}

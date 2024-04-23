package com.knowledgetransfer.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProcessoRecorrente {
    private Long id;
    private String nome;
    private String descricao;
    private Long recorrencia;

}

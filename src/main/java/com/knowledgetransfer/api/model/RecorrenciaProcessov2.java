package com.knowledgetransfer.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RecorrenciaProcessov2 {
    private String nome;

    private String descricao;


    private Long recorrencia;


}

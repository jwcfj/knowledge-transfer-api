package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public record AtualizacaoFormularioDTO(
        @NotNull
        Long id,
        String nome,
        List<String> perguntas,
        List<List<String>> alternativas,
        List<List<Long>> processos_id){}

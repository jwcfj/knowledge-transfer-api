package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;


public record AtualizacaoFormularioDTO(
        @NotNull
        Long id,
        String nome,
        List<AtualizacaoQuestaoDTO> questoes
){}

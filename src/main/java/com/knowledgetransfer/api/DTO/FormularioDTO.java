package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record FormularioDTO(
        @NotNull
        String nome,
        @NotNull
        List<String>perguntas,
        @NotNull
        List<List<String>> alternativas,
        @NotNull
        List<List<String>> processos_alternativas) {
}

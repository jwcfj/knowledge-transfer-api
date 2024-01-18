package com.knowledgetransfer.api.DTO;

import jakarta.validation.constraints.NotNull;
import org.aspectj.weaver.ast.Not;

import java.util.List;

public record QuestaoDTO(

        @NotNull
        String pergunta,
        @NotNull
        List<String> alternativas,
        @NotNull
        List<String> processos_alternativas) {
}

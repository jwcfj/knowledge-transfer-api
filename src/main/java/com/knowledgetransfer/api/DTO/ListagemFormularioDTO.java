package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Formulario;
import com.knowledgetransfer.api.model.Questao;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.stream.Collectors;

public record  ListagemFormularioDTO(
        Long id,
        String nome,
        List<ListagemQuestaoDTO> questoes
) {
    public ListagemFormularioDTO(Formulario formulario){
        this(formulario.getId(),formulario.getNome(), mapQuestoes(formulario.getQuestionario()));
    }
    private static List<ListagemQuestaoDTO> mapQuestoes(List<Questao> questoes) {
        return questoes.stream().map(ListagemQuestaoDTO::new).collect(Collectors.toList());
    }
}

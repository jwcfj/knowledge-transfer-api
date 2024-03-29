package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Formulario;

import java.util.List;

public record EncontrarFormularioDTO(
        Long id,
        String nome,
        List<ListagemQuestaoDTO> questoes
) {
}

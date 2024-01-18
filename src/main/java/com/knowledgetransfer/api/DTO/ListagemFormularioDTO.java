package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Formulario;
import jakarta.validation.constraints.NotNull;

public record ListagemFormularioDTO(
        Long id,
        String nome

) {
    public ListagemFormularioDTO(Formulario formulario){
        this(formulario.getId(),formulario.getNome());
    }
}

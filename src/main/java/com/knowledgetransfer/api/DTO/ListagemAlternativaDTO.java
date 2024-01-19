package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Alternativa;
import com.knowledgetransfer.api.model.Formulario;

public record ListagemAlternativaDTO(
        Long id,
        String alternativa
) {
    public ListagemAlternativaDTO(Alternativa alternativa){
        this(alternativa.getId(), alternativa.getAlternativa());
    }
}

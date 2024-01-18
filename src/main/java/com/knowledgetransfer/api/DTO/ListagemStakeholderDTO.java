package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.Stakeholder;

public record ListagemStakeholderDTO(
        Long id,
        String nome

) {
    public ListagemStakeholderDTO(Stakeholder stakeholder){
        this(stakeholder.getId(),stakeholder.getNome());
    }
}

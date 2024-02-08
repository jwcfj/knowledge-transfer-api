package com.knowledgetransfer.api.DTO;

import com.knowledgetransfer.api.model.FormularioRespondido;
import com.knowledgetransfer.api.model.Stakeholder;

import java.util.List;
import java.util.stream.Collectors;

public record ListagemStakeholderDTO(
        Long id,
        String nome,
        String cpf,
        String email,
        String filiacao,
        String cargo,
        String senioridade,
        //falta implemnetar o retorno dos ids dos formularios respondidos pelo stakeholder
        List<ListagemFormularioRespondidoDTO> formulariosRespondidos

) {
    public ListagemStakeholderDTO(Stakeholder stakeholder){
        this(stakeholder.getId(),stakeholder.getNome(),stakeholder.getCpf(),stakeholder.getEmail(),stakeholder.getFiliacao(), stakeholder.getCargo(), stakeholder.getSenioridade(), mapFormulariosRespondidos(stakeholder.getFormulariosRespondidos()));
    }

    public static List<ListagemFormularioRespondidoDTO> mapFormulariosRespondidos(List<FormularioRespondido> formulariosRespondidos){
        return formulariosRespondidos.stream().map(ListagemFormularioRespondidoDTO::new).collect(Collectors.toList());
    }
}

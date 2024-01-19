package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.StakeholderDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "stakeholders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Stakeholder extends Usuario{

    private String senioridade;
    private String cargo;

    @OneToMany(mappedBy = "stakeholder", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FormularioRespondido> formulariosRespondidos;

    public Stakeholder(StakeholderDTO dados) {
        this.setFiliacao(dados.filiacao());
        this.setEmail(dados.email());
        this.setNome(dados.nome());
        this.setCpf(dados.cpf());
        this.setSenioridade(dados.senioridade());
        this.setCargo(dados.cargo());


    }
}

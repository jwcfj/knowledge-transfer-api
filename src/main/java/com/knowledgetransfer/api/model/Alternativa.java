package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.AlternativaDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Table(name ="alternativas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Alternativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String alternativa;

    //private String processo;

    @ManyToOne
    private  Questao questao;

    @ManyToOne
    private Processo processo;

    @OneToMany(mappedBy = "alternativa", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RespostaQuestao> respostasQuestoes;

    public Alternativa(AlternativaDTO dados) {
        this.setAlternativa(dados.alternativa());
        //this.setProcesso(dados.processo());
    }

}

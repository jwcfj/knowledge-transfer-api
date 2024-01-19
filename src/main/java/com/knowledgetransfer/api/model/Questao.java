package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.AlternativaDTO;
import com.knowledgetransfer.api.DTO.QuestaoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name ="questoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Questao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pergunta;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Alternativa> alternativas;

    @OneToMany(mappedBy = "questao", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<RespostaQuestao> respostasQuestoes;

    @ManyToOne
    private  Formulario formulario;

    public Questao(QuestaoDTO dados){
        this.setPergunta(dados.pergunta());
        alternativas = new ArrayList<>();
        for(int i=0; i<dados.alternativas().size();i++){
            Alternativa novaAlternativa = new Alternativa(new AlternativaDTO(dados.alternativas().remove(0),dados.processos_alternativas().remove(0)));
            novaAlternativa.setQuestao(this);
            alternativas.add(novaAlternativa);
        }


    }

}

package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.FormularioDTO;
import com.knowledgetransfer.api.DTO.QuestaoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "formularios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;
    //@Embedded
    //privat List<Questao> questionario;
    /*private List<String> perguntas;
    private List<List<String>> alternativas;
    private List<List<String>> processos_alternativas;
    */

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<FormularioRespondido> formulariosRespondidos;

    @OneToMany(mappedBy = "formulario", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Questao> questionario;

    /*public Formulario(FormularioDTO dados){
        this.nome= dados.nome();
        questionario = new ArrayList<>();
        for(int i=0;i<dados.perguntas().size();i++){
            Questao novaQuestao = new Questao(new QuestaoDTO(dados.perguntas().remove(0),dados.alternativas().remove(0),dados.processos_alternativas().remove(0)));
            novaQuestao.setFormulario(this);
            questionario.add(novaQuestao);

        }
        //this.setAlternativas(dados.alternativas());
        //this.setPerguntas(dados.perguntas());
        //this.setProcessos_alternativas(dados.processos_alternativas());
    }*/
}

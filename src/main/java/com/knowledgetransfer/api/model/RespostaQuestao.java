package com.knowledgetransfer.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "respostas_questoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class RespostaQuestao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne
    private FormularioRespondido formulario_respondido;
    @ManyToOne
    private Questao questao;
    @ManyToOne
    private Alternativa alternativa;

//a propriedade da alternativa

}

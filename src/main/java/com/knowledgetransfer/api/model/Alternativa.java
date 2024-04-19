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
    private String pergunta;

    //private String processo;

//    @ManyToOne
//    private  Questao questao;

    @ManyToOne
    private Processo processo;


    public Alternativa(AlternativaDTO dados) {
        //this.setAlternativa(dados.alternativa());
        this.setPergunta(dados.pergunta());
        //this.setProcesso(dados.processo());
    }

}

package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.AlternativaDTO;
import jakarta.persistence.*;
import lombok.*;


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
    private String processo;

    @ManyToOne
    private  Questao questao;

    public Alternativa(AlternativaDTO dados) {
        this.setAlternativa(dados.alternativa());
        this.setProcesso(dados.processo());
    }

}

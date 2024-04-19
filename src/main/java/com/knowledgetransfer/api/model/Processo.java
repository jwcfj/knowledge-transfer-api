package com.knowledgetransfer.api.model;

import com.knowledgetransfer.api.DTO.ProcessoDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "processos")
@EqualsAndHashCode(of = "id")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "processo", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<Alternativa> alternativas;

    public Processo(ProcessoDTO dados){
        this.setNome(dados.nome());
        this.setDescricao(dados.descricao());

    }
    //@Embedded
    //private Etapa etapa;


}

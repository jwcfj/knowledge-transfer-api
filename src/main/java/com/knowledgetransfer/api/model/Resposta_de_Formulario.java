package com.knowledgetransfer.api.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;
@Entity
@Table(name = "respostas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")

public class Resposta_de_Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //
    private Timestamp data_criacao;

    //private List<String> propriedades;

    @ManyToOne
    private Stakeholder stakeholder;
    @ManyToOne
    private Formulario formulario;

    @ManyToOne
    private Processo processo;

    /*@ManyToMany(mappedBy = "respostaDeFormulario", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "respostas",
            joinColumns = @JoinColumn(name = "resposta_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "processo_id", referencedColumnName = "id"))
    private List<Processo> processos;*/


}

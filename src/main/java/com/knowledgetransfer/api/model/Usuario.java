package com.knowledgetransfer.api.model;

import jakarta.persistence.*;
import lombok.*;

//@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String filiacao;
    private String email;
    private String nome;
    @Column(unique = true)
    private String cpf;


}

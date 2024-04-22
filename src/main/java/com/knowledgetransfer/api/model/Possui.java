package com.knowledgetransfer.api.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name ="possui")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Possui {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Alternativa alternativa;

    @ManyToOne
    private Processo processo;
}

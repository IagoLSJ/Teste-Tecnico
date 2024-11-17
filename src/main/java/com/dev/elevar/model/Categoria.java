package com.dev.elevar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Categoria {
    @Id
    @SequenceGenerator(name = "categoria_id_seq", sequenceName = "categoria_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_id_seqgit a")
    private Integer id;

    @Column(unique = true, nullable = false)
    private String nome;

    private String descricao;
}

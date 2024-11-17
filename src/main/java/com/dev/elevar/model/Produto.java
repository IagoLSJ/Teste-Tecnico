package com.dev.elevar.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Produto {
    @Id
    @SequenceGenerator(name = "produto_id_seq", sequenceName = "produto_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produto_id_seq")
    private Integer id;

    private String nome;

    private int quantidade;

    private String codigo;

    private String descricao;

    @Column(precision = 15, scale = 2)
    private Double valor;

    private Boolean status;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ImagemProduto> imagens;
}

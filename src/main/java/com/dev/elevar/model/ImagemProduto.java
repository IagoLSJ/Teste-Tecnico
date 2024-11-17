package com.dev.elevar.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ImagemProduto {
    @Id
    @SequenceGenerator(name = "imagem_produto_id_seq", sequenceName = "imagem_produto_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "imagem_produto_id_seq")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    @JsonBackReference
    private Produto produto;

    private String url;

    private String nome;

    private LocalDateTime dataUpload;
}

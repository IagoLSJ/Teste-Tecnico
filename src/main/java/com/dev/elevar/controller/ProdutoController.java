package com.dev.elevar.controller;

import com.dev.elevar.model.Produto;
import com.dev.elevar.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/produto")
@Tag(name = "Produto", description = "Endpoints para gerenciamento de produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    @Operation(summary = "Listar produtos", description = "Lista todos os produtos com filtros opcionais.")
    public ResponseEntity<List<Produto>> findAll(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "valorMinimo", required = false) Double valorMinimo,
            @RequestParam(value = "valorMaximo", required = false) Double valorMaximo,
            @RequestParam(value = "status", required = false) Boolean status,
            @RequestParam(value = "categoriaNome", required = false) String categoriaNome
    ) {
        List<Produto> produtos = produtoService.findAll(nome, valorMinimo, valorMaximo, status, categoriaNome);
        return ResponseEntity.ok(produtos);
    }

    @GetMapping("/id/{id}")
    @Operation(summary = "Buscar produto por ID", description = "Busca um produto pelo seu ID.")
    public ResponseEntity<Produto> findById(@PathVariable("id") Integer id) {
        Produto produto = produtoService.findById(id);
        return ResponseEntity.ok(produto);
    }

    @PostMapping("/")
    @Operation(summary = "Criar um novo produto", description = "Cria um novo produto.")
    public ResponseEntity<Produto> create(@Valid @RequestBody Produto produto) {
        Produto novoProduto = produtoService.create(produto);
        return ResponseEntity.status(201).body(novoProduto);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar um produto", description = "Atualiza os dados de um produto existente pelo ID.")
    public ResponseEntity<Produto> update(@PathVariable("id") Integer id, @Valid @RequestBody Produto produto) {
        Produto produtoAtualizado = produtoService.update(id, produto);
        return ResponseEntity.ok(produtoAtualizado);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir um produto", description = "Remove um produto do sistema pelo ID.")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        produtoService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

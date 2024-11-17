package com.dev.elevar.controller;

import com.dev.elevar.model.Produto;
import com.dev.elevar.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/")
    public List<Produto> findAll(
            @RequestParam(value = "nome", required = false) String nome,
            @RequestParam(value = "valorMinimo", required = false) Double valorMinimo,
            @RequestParam(value = "valorMaximo", required = false) Double valorMaximo,
            @RequestParam(value = "status", required = false) Boolean status,
            @RequestParam(value = "categoriaNome", required = false) String categoriaNome
    ) {
        return produtoService.findAll(nome, valorMinimo, valorMaximo, status, categoriaNome);
    }

    @GetMapping("/id/{id}")
    public Produto findById(@PathVariable("id") Integer id) {
        return produtoService.findById(id);
    }

    @PostMapping("/")
    public Produto create(@RequestBody Produto produto) {
        return produtoService.create(produto);
    }

    @PutMapping("/{id}")
    public Produto update(@PathVariable("id") Integer id, @RequestBody Produto produto) {
        return produtoService.update(id, produto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        produtoService.delete(id);
    }
}

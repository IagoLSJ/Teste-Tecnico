package com.dev.elevar.controller;

import com.dev.elevar.model.Categoria;
import com.dev.elevar.service.CategoriaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/categoria")
@Tag(name = "Categoria", description = "Endpoints para gerenciamento de categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    @Operation(summary = "Listar todas as categorias", description = "Retorna uma lista de todas as categorias cadastradas.")
    public ResponseEntity<List<Categoria>> findAll() {
        return ResponseEntity.ok(categoriaService.findAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Buscar categoria por ID", description = "Retorna uma categoria baseada no seu ID.")
    public ResponseEntity<Categoria> findById(@PathVariable("id") Integer id) {
        Categoria categoria = categoriaService.findById(id);
        return ResponseEntity.ok(categoria);
    }

    @GetMapping("/nome/{nome}")
    @Operation(summary = "Buscar categoria por nome", description = "Retorna uma categoria baseada no nome.")
    public ResponseEntity<Categoria> findByNome(@PathVariable("nome") String nome) {
        Categoria categoria = categoriaService.findByNome(nome);
        return ResponseEntity.ok(categoria);
    }

    @PostMapping("/")
    @Operation(summary = "Criar uma nova categoria", description = "Cria uma nova categoria no sistema.")
    public ResponseEntity<Categoria> create(@Valid @RequestBody Categoria categoria) {
        Categoria novaCategoria = this.categoriaService.create(categoria);
        return ResponseEntity.status(201).body(novaCategoria);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Atualizar uma categoria", description = "Atualiza os dados de uma categoria existente pelo ID.")
    public ResponseEntity<Categoria> update(@PathVariable("id") Integer id, @Valid @RequestBody Categoria categoria) {
        Categoria categoriaAtualizada = this.categoriaService.update(id, categoria);
        return ResponseEntity.ok(categoriaAtualizada);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Excluir uma categoria", description = "Remove uma categoria do sistema pelo ID.")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        this.categoriaService.delete(id);
        return ResponseEntity.noContent().build();
    }
}

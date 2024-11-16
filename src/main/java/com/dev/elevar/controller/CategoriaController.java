package com.dev.elevar.controller;

import com.dev.elevar.model.Categoria;
import com.dev.elevar.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/")
    public List<Categoria> findAll() {
        return categoriaService.findAll();
    }

    @GetMapping("/id/{id}")
    public Categoria findById(@PathVariable("id") Integer id) {
        return categoriaService.findById(id);
    }

    @GetMapping("/nome/{nome}")
    public Categoria findByNome(@PathVariable("nome") String nome) {
        return categoriaService.findByNome(nome);
    }

    @PostMapping("/")
    public Categoria create(@RequestBody Categoria categoria) {
        return this.categoriaService.create(categoria);
    }

    @PutMapping("/{id}")
    public Categoria update(@PathVariable("id") Integer id, @RequestBody Categoria categoria) {
        return this.categoriaService.update(id, categoria);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        this.categoriaService.delete(id);
    }

}

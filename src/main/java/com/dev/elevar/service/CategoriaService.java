package com.dev.elevar.service;

import com.dev.elevar.exceptions.ResourceNotFoundException;
import com.dev.elevar.exceptions.ResourceConflictException;
import com.dev.elevar.model.Categoria;
import com.dev.elevar.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Categoria> findAll() {
        return this.categoriaRepository.findAll();
    }

    public Categoria findById(Integer id) {
        return this.categoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categoria não encontrada para o ID: " + id));
    }

    public Categoria findByNome(String nome) {
        Categoria categoria = this.categoriaRepository.findByNome(nome);

        if (categoria == null) {
            throw new ResourceNotFoundException("Categoria não encontrada para o nome: " + nome);
        }
        return categoria;
    }

    public Categoria create(Categoria categoria) {
        if (this.categoriaRepository.findByNome(categoria.getNome()) != null) {
            throw new ResourceConflictException("Já existe uma categoria com o nome: " + categoria.getNome());
        }

        return this.categoriaRepository.save(categoria);
    }

    public Categoria update(Integer id, Categoria categoria) {
        Categoria categoriaById = this.findById(id);

        Categoria nomeDisponivel = this.categoriaRepository.findByNome(categoria.getNome());
        if (nomeDisponivel != null && !nomeDisponivel.getId().equals(id)) {
            throw new ResourceConflictException("Já existe uma categoria com o nome: " + categoria.getNome());
        }

        if (categoria.getDescricao() != null) {
            categoriaById.setDescricao(categoria.getDescricao());
        }

        if (categoria.getNome() != null) {
            categoriaById.setNome(categoria.getNome());
        }

        return this.categoriaRepository.save(categoriaById);
    }

    public void delete(Integer id) {
        Categoria categoria = this.findById(id);
        this.categoriaRepository.delete(categoria);
    }
}

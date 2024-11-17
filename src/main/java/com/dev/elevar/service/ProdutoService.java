package com.dev.elevar.service;

import com.dev.elevar.exceptions.ResourceConflictException;
import com.dev.elevar.exceptions.ResourceNotFoundException;
import com.dev.elevar.model.Categoria;
import com.dev.elevar.model.Produto;
import com.dev.elevar.repository.CategoriaRepository;
import com.dev.elevar.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<Produto> findAll(String nome, Double valorMinimo, Double valorMaximo, Boolean status, String categoriaNome) {
        if(nome == null && valorMinimo == null && valorMaximo == null && status == null && categoriaNome == null) {
            return produtoRepository.findAll();
        }else{
            return produtoRepository.findByFilters(nome, valorMinimo, valorMaximo, status, categoriaNome);
        }
    }

    public Produto findById(Integer id) {
        return this.produtoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrada para o ID: " + id));
    }

    public Produto create(Produto produto) {
        if(produto.getCategoria() != null){
            Optional<Categoria> categoria = categoriaRepository.findById(produto.getCategoria().getId());
            if(!categoria.isPresent()) {
                throw new ResourceNotFoundException("Categoria informada não existe");
            }
        }

        if(produto.getQuantidade() < 0){
            throw new ResourceConflictException("Não e possivel cadastrar um produto com quantidade negativa");
        }
        if(produto.getValor() <=0){
            throw new ResourceConflictException("Não e possivel cadastrar um produto com valor negativo");
        }

        return this.produtoRepository.save(produto);
    }

    public Produto update(Integer id, Produto produto) {
        Produto produtoById = this.produtoRepository.findById(id).get();
        if(produtoById == null) {
            throw new ResourceNotFoundException("Produto não encontrado para o ID: " + id);
        }
        if (produto.getNome() != null) {
            produtoById.setNome(produto.getNome());
        }

        if (produto.getQuantidade() >= 0) {
            produtoById.setQuantidade(produto.getQuantidade());
        }

        if (produto.getCodigo() != null) {
            produtoById.setCodigo(produto.getCodigo());
        }

        if (produto.getDescricao() != null) {
            produtoById.setDescricao(produto.getDescricao());
        }

        if (produto.getValor() != null && produto.getValor() >= 0) {
            produtoById.setValor(produto.getValor());
        }

        if (produto.getStatus() != null) {
            produtoById.setStatus(produto.getStatus());
        }

        if (produto.getCategoria() != null) {
            produtoById.setCategoria(produto.getCategoria());
        }

        return this.produtoRepository.save(produtoById);
    }

    public void delete(Integer id){
        Produto produtoById = this.findById(id);
        this.produtoRepository.delete(produtoById);
    }





}

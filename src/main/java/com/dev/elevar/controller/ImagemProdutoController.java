package com.dev.elevar.controller;

import com.dev.elevar.model.ImagemProduto;
import com.dev.elevar.service.ImagemProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagem-produto")
public class ImagemProdutoController {
    @Autowired
    private ImagemProdutoService imagemProdutoService;

    @PostMapping("/upload/{produtoId}")
    public ImagemProduto uploadImagem(@PathVariable Integer produtoId, @RequestParam("file") MultipartFile file) {
        return imagemProdutoService.uploadImagem(produtoId, file);
    }

    @DeleteMapping("/delete/{imagemProdutoId}")
    public void delete(@PathVariable Integer imagemProdutoId) {
        this.imagemProdutoService.removeImage(imagemProdutoId);
    }
}

package com.dev.elevar.controller;

import com.dev.elevar.model.ImagemProduto;
import com.dev.elevar.service.ImagemProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/imagem-produto")
@Tag(name = "Imagem Produto", description = "Endpoints para gerenciamento de imagens de produtos")
public class ImagemProdutoController {

    @Autowired
    private ImagemProdutoService imagemProdutoService;

    @PostMapping("/upload/{produtoId}")
    @Operation(summary = "Fazer upload de imagem", description = "Realiza o upload de uma imagem para um produto.")
    public ResponseEntity<ImagemProduto> uploadImagem(
            @PathVariable Integer produtoId,
            @RequestParam("file") MultipartFile file
    ) {
        ImagemProduto imagemProduto = imagemProdutoService.uploadImagem(produtoId, file);
        return ResponseEntity.status(201).body(imagemProduto);
    }

    @DeleteMapping("/delete/{imagemProdutoId}")
    @Operation(summary = "Remover imagem", description = "Remove uma imagem de um produto pelo ID da imagem.")
    public ResponseEntity<Void> delete(@PathVariable Integer imagemProdutoId) {
        imagemProdutoService.removeImage(imagemProdutoId);
        return ResponseEntity.noContent().build();
    }
}

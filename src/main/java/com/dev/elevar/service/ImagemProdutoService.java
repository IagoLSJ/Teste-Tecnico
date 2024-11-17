package com.dev.elevar.service;

import com.dev.elevar.exceptions.ResourceNotFoundException;
import com.dev.elevar.model.ImagemProduto;
import com.dev.elevar.model.Produto;
import com.dev.elevar.repository.ImagemProdutoRepository;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class ImagemProdutoService {
    @Autowired
    private MinioClient minioClient;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ImagemProdutoRepository imagemProdutoRepository;

    private static final String BUCKET_NAME = "elevar";

    public ImagemProduto uploadImagem(Integer produtoId, MultipartFile file) {
        Produto produtoById = this.produtoService.findById(produtoId);

        try{
            String nomeImagemProduto = UUID.randomUUID() + "_" + file.getOriginalFilename();

            minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(BUCKET_NAME)
                            .object(nomeImagemProduto)
                            .contentType(file.getContentType())
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .build()
            );

            ImagemProduto imagemProduto  = new ImagemProduto();
            imagemProduto.setProduto(produtoById);
            imagemProduto.setUrl("http://localhost:9000/"+ BUCKET_NAME + "/" + nomeImagemProduto);
            imagemProduto.setNome(nomeImagemProduto);
            imagemProduto.setDataUpload(LocalDateTime.now());
            return this.imagemProdutoRepository.save(imagemProduto);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    public void removeImage(Integer id){
        ImagemProduto imagemProduto = this.imagemProdutoRepository.findById(id).get();

        if(imagemProduto == null){
            throw new ResourceNotFoundException("Imagem produto não encontrado para o ID:" + id);
        }

        try{

        minioClient.removeObject(RemoveObjectArgs.builder()
                        .bucket(BUCKET_NAME)
                        .object(imagemProduto.getNome())
                .build());
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}

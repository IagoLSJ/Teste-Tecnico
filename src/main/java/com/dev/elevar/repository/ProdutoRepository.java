package com.dev.elevar.repository;

import com.dev.elevar.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query("SELECT p FROM Produto p " +
            "LEFT JOIN p.categoria c "+
            "WHERE (:nome IS NULL OR p.nome LIKE %:nome%) " +
            "AND (:valorMinimo IS NULL OR p.valor >= :valorMinimo) " +
            "AND (:valorMaximo IS NULL OR p.valor <= :valorMaximo) " +
            "AND (:status IS NULL OR p.status = :status) " +
            "AND (:categoriaNome IS NULL or c.nome LIKE %:categoriaNome%)"
    )
    List<Produto> findByFilters(@Param("nome") String nome,
                                @Param("valorMinimo") Double valorMinimo,
                                @Param("valorMaximo") Double valorMaximo,
                                @Param("status") Boolean status,
                                @Param("categoriaNome") String categoriaNome);
}

package com.api.senac.filme.data;

import com.api.senac.filme.entity.Analise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nicolas
 */
@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Integer>{
    
    @Query("SELECT a FROM Analise a WHERE a.filme.id = :filmeId")
    List<Analise> findByFilmeId(@Param("filmeId") Integer filmeId);
}

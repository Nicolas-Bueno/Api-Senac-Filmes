package com.api.senac.filme.data;

import com.api.senac.filme.entity.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Nicolas
 */
@Repository
public interface FilmeRepository extends JpaRepository<Filme, Integer>{
    
}

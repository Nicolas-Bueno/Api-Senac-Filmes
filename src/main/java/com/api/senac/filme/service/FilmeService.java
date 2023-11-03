package com.api.senac.filme.service;

import com.api.senac.filme.data.FilmeRepository;
import com.api.senac.filme.entity.Filme;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nicolas
 */
@Service
public class FilmeService {
    
    @Autowired
    FilmeRepository filmeRepository;
    
    //post
    public Filme cadastraFilme(Filme filme){
        filmeRepository.save(filme);
        return filme;
    }
    
    //get id
    public Filme getFilmeId(Integer filmeId){
        return filmeRepository.findById(filmeId).orElse(null);
    }
    
    //put
    public Filme atualizarFilme(Integer filmeId, Filme filmeRequest){
        Filme filme = getFilmeId(filmeId);
        
        filme.setNome(filmeRequest.getNome());
        filme.setSinopse(filmeRequest.getSinopse());
        filme.setGenero(filmeRequest.getGenero());
        filme.setAnoLancamento(filmeRequest.getAnoLancamento());
        
        filmeRepository.save(filme);
        
        return filme;
    }
    
    //get all
    public List<Filme> listarTodosFilmes(){
        return filmeRepository.findAll();
    }
    
    //delete
    public void deletarFilme(Integer filmeId){
        Filme filme = getFilmeId(filmeId);
        
        filmeRepository.deleteById(filme.getId());
    }
    
}

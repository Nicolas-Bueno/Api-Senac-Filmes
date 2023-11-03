package com.api.senac.filme.service;

import com.api.senac.filme.data.AnaliseRepository;
import com.api.senac.filme.entity.Analise;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Nicolas
 */
@Service
public class AnaliseService {

    @Autowired
    AnaliseRepository analiseRepository;

    public Analise criaAnalise(Analise analise) {
        analiseRepository.save(analise);
        return analise;
    }

    //retorna todas as analise de um filme 
    public List<Analise> buscarAnalisesPorFilmeId(Integer filmeId) {
        return analiseRepository.findByFilmeId(filmeId);
    }

    public Analise atualizarAnalise(Analise analise) {
        // Verifique se a análise com o ID existe no banco de dados
        Analise existingAnalise = analiseRepository.findById(analise.getId()).orElse(null);

        if (existingAnalise != null) {
            // Atualize os campos da análise existente com os novos valores
            existingAnalise.setTextoAnalise(analise.getTextoAnalise());
            existingAnalise.setNota(analise.getNota());

            // Salve a análise atualizada no banco de dados
            return analiseRepository.save(existingAnalise);
        } else {
            return null;
        }
    }

    public void excluirAnalise(Integer analiseId) {
        // Verifique se a análise com o ID existe no banco de dados
        Analise existingAnalise = analiseRepository.findById(analiseId).orElse(null);

        if (existingAnalise != null) {
            // Exclua a análise existente do banco de dados
            analiseRepository.delete(existingAnalise);
        } else {
            // Trate o caso em que a análise com o ID fornecido não existe
            // Você pode lançar uma exceção ou retornar uma resposta adequada, dependendo da sua lógica de negócios.
        }
    }

}

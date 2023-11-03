package com.api.senac.filme.controller;

import com.api.senac.filme.entity.Analise;
import com.api.senac.filme.service.AnaliseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Nicolas
 */
@RestController
@RequestMapping("/analises")
public class AnaliseController {
    @Autowired
    private AnaliseService analiseService;


    @PostMapping("/adicionar")
    public ResponseEntity<Analise> criarAnalise(@RequestBody Analise analise) {
        Analise novaAnalise = analiseService.criaAnalise(analise);
        return new ResponseEntity<>(novaAnalise, HttpStatus.CREATED);
    }

    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Analise> atualizarAnalise(@RequestBody Analise analise) {
        Analise updatedAnalise = analiseService.atualizarAnalise(analise);
        if (updatedAnalise != null) {
            return new ResponseEntity<>(updatedAnalise, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @DeleteMapping("/excluir/{id}")
    public ResponseEntity<Void> excluirAnalise(@PathVariable Integer analiseId) {
        analiseService.excluirAnalise(analiseId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Endpoint para buscar todas as análises de um filme por ID do filme
    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<Analise>> buscarAnalisesPorFilme(@PathVariable Integer filmeId) {
        List<Analise> analises = analiseService.buscarAnalisesPorFilmeId(filmeId);
        return new ResponseEntity<>(analises, HttpStatus.OK);
    }
}

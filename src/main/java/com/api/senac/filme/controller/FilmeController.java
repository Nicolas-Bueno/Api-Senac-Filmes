/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.api.senac.filme.controller;

import com.api.senac.filme.entity.Filme;
import com.api.senac.filme.service.FilmeService;
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
@RequestMapping("/filme")
public class FilmeController {
    
    @Autowired
    FilmeService filmeService;
    
    @GetMapping("/listar")
    public ResponseEntity<List> getAllFilmes(){
        List<Filme> filmes = filmeService.listarTodosFilmes();
        
        return new ResponseEntity<>(filmes, HttpStatus.OK);        
    }
    
    @GetMapping("/pesquisar/{id}")
    public ResponseEntity<Filme> getFilmeById(@PathVariable Integer id){
        Filme filme = filmeService.getFilmeId(id);
        
        return new ResponseEntity<>(filme, HttpStatus.OK);
    }
    
    @PostMapping("/adicionar")
    public ResponseEntity<Filme> addFilme(@RequestBody Filme filme){
        var novoFilme = filmeService.cadastraFilme(filme);
        
        return new ResponseEntity<>(novoFilme, HttpStatus.CREATED);
    }
    
    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Filme> atualizarFilme(@PathVariable Integer id, @RequestBody Filme filme){
        var filmeAtualizado = filmeService.atualizarFilme(id, filme);
        
        return new ResponseEntity<>(filmeAtualizado, HttpStatus.OK);
    }
    
    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<Filme> deletarFilme(@PathVariable Integer id){
        filmeService.deletarFilme(id);
        
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
}

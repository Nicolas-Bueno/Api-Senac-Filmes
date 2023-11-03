package com.api.senac.filme.controller;

import com.api.senac.filme.entity.Filme;
import com.api.senac.filme.service.FilmeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

/**
 *
 * @author Nicolas
 */
@Controller 
public class FilmeWebController {
    
    @Autowired
    FilmeService filmeService;
    
    //Get All Filmes
    @GetMapping("/")
    public String viewHomePage(Model model){
        model.addAttribute("listarFilmes", filmeService.listarTodosFilmes());
        return "index";
    }
    
    //Delete
    @GetMapping("/deletarFilme/{id}")
    public String deletarFilme(@PathVariable(value = "id") Integer id){
        filmeService.deletarFilme(id);
        return "redirect:/";
    }
    
    //Get form
    @GetMapping("/criaFilmeForm")
    public String criaFilmeForm(Model model){
        Filme filme = new Filme();
        model.addAttribute("filme", filme);
        return "inserir";
    }
    
    //Post
    public String salvarFilme(@Valid @ModelAttribute("filme") Filme filme, BindingResult result){
        if (result.hasErrors()) {
            return "inserir";
        }
        
        if (filme.getId() == null) {
            filmeService.cadastraFilme(filme);
        } else {
            filmeService.atualizarFilme(filme.getId(), filme);
        }
        return "redirect:/";
    }
    
    //Put
    @GetMapping("/atualizarFilmeForm/{id}")
    public String atualizarFilmeForm(@PathVariable(value = "id") Integer id, Model model){
        Filme filme = filmeService.getFilmeId(id);
        
        model.addAttribute("filme", filme);
        
        return "atualizar";
    }
    
}

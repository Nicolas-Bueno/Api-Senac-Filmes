package com.api.senac.filme.controller;

import com.api.senac.filme.entity.Filme;
import com.api.senac.filme.entity.Preferencia;
import com.api.senac.filme.service.FilmeService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    public String viewHomePage(@CookieValue(name="pref-nome", defaultValue="") String nome,
            @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            Model model){
        model.addAttribute("listarFilmes", filmeService.listarTodosFilmes());
        model.addAttribute("nome", nome);
        model.addAttribute("css", tema);
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
    public String criaFilmeForm(@CookieValue(name="pref-nome", defaultValue="") String nome,
            @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            Model model){
        Filme filme = new Filme();
        model.addAttribute("filme", filme);
        model.addAttribute("nome", nome);
        model.addAttribute("css", tema);
        return "inserir";
    }
    
    //Post
    @PostMapping("/salvarFilme")
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
    public String atualizarFilmeForm(@CookieValue(name="pref-nome", defaultValue="") String nome,
            @CookieValue(name="pref-estilo", defaultValue="claro")String tema,
            @PathVariable(value = "id") Integer id, Model model){
        Filme filme = filmeService.getFilmeId(id);
        
        model.addAttribute("filme", filme);
        model.addAttribute("nome", nome);
        model.addAttribute("css", tema);
        
        return "atualizar";
    }
    
}

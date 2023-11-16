package com.api.senac.filme.controller;

import com.api.senac.filme.entity.Analise;
import com.api.senac.filme.entity.Filme;
import com.api.senac.filme.service.AnaliseService;
import com.api.senac.filme.service.FilmeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author Nicolas
 */
@Controller
public class AnaliseWebController {

    @Autowired
    AnaliseService analiseService;

    @Autowired
    FilmeService filmeService;

    @GetMapping("/adicionaAnalise/{idFilme}")
    public String getFormAnalise(@PathVariable(value = "idFilme") Integer id, Model model) {
        Filme filme = filmeService.getFilmeId(id);
        Analise analise = new Analise();
        analise.setFilme(filme);

        model.addAttribute("analise", analise);
        model.addAttribute("filme", filme);

        return "inserirAnalise";
    }

    @PostMapping("/adicionaAnalise/{idFilme}")
    public String postFormAnalise(@PathVariable("idFilme") Integer filmeId, @ModelAttribute Analise analise, Model model) {

        Filme filme = filmeService.getFilmeId(filmeId);
        if (filme != null) {
            analise.setFilme(filme);
            analiseService.criaAnalise(analise);
            return "redirect:/";
        } else {
            return "erro";
        }
    }

    // Método para exibir o formulário de atualização de análise
    @GetMapping("/atualizarAnalise/{id}")
    public String atualizarAnaliseForm(@PathVariable(value = "id") Integer id, Model model) {
        Analise analise = analiseService.buscarAnalisePorId(id);

        model.addAttribute("analise", analise);

        return "atualizarAnalise";
    }

    //(requisição PUT)
    @PostMapping("/atualizarAnalise/{id}")
    public String atualizarAnalise(@PathVariable(value = "id") Integer id, @ModelAttribute Analise analise, Model model) {
        Analise analiseExistente = analiseService.buscarAnalisePorId(id);

        if (analiseExistente != null) {
            analiseExistente.setTextoAnalise(analise.getTextoAnalise());
            analiseExistente.setNota(analise.getNota());

            analiseService.atualizarAnalise(analiseExistente);

            return "redirect:/";
        } else {
            return "erro";
        }
    }

    @GetMapping("/analises")
    public String listarAnalises(Model model) {
        List<Analise> listarAnalises = analiseService.listarTodasAnalises();
        model.addAttribute("listarAnalises", listarAnalises);
        return "analises";
    }

    @GetMapping("/deletarAnalise/{id}")
    public String deletarAnalise(@PathVariable(value = "id") Integer id) {
        analiseService.excluirAnalise(id);
        return "redirect:/analises";
    }

}

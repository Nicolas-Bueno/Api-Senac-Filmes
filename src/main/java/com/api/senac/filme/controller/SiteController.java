package com.api.senac.filme.controller;


import com.api.senac.filme.entity.Preferencia;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Nicolas
 */
@Controller
public class SiteController {
    @RequestMapping("/preferencias")
    public String preferencias(){
        return "preferencias";
    }
    
    @PostMapping("/preferencias")
    public ModelAndView gravaPreferencias(@ModelAttribute Preferencia pref,
            HttpServletResponse response){
        
        Cookie cookiePrefNome = new Cookie("pref-nome", pref.getNome());
        cookiePrefNome.setDomain("localhost"); //disponível apenas no domínio "localhost"
        cookiePrefNome.setHttpOnly(true); //acessível apenas por HTTP, JS não
        cookiePrefNome.setMaxAge(86400); //1 dia
        response.addCookie(cookiePrefNome);
        Cookie cookiePrefEstilo = new Cookie("pref-estilo", pref.getEstilo());
        cookiePrefEstilo.setDomain("localhost"); //disponível apenas no domínio "localhost" 
        cookiePrefEstilo.setHttpOnly(true); //acessível apenas por HTTP, JS não 
        cookiePrefEstilo.setMaxAge(86400); //1 dia 
        response.addCookie(cookiePrefEstilo);
        return new ModelAndView("redirect:/"); //"index";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.dao.implementation.ZaposleniDAO;
import com.aleksandra.domen.Zaposleni;
import com.aleksandra.service.ZaposleniService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
public class ZaposleniController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView check_user() {
        ModelAndView mv = new ModelAndView("login");
        mv.addObject("zaposleni", new Zaposleni());
        return mv;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView check_user(@ModelAttribute("zaposleni") Zaposleni zaposleni) {
        ZaposleniService zaposleniS = new ZaposleniService();
        Zaposleni zap = new Zaposleni();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("main");
            zaposleni = zaposleniS.proveriZaposlenog(zaposleni.getKorisnickoIme(), zaposleni.getLozinka());
            mv.addObject("zaposleni", zap);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("login");
            mv.addObject("error", ex.getMessage());
        }
        return mv;
    }
}

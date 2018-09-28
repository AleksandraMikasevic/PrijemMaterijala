/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.domen.JedinicaMere;
import com.aleksandra.domen.Materijal;
import com.aleksandra.service.MaterijalService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/material")
public class MaterijalController {

    @RequestMapping("/all_materials")
    public ModelAndView all_materials() {
        MaterijalService materijal = new MaterijalService();
        List<Materijal> materijali = new ArrayList<>();
        try {
            materijali = materijal.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_materials");
        mv.addObject("listaMaterijala", materijali);
        System.out.println("sandra svi materijali");
        return mv;
    }

    @RequestMapping(value = "/add_material", method = RequestMethod.GET)
    public ModelAndView add_material_get() {
        ModelAndView mv = new ModelAndView("add_material", "material", new Materijal());
        mv.addObject("jedniceMere", JedinicaMere.values());
        return mv;
    }

    @RequestMapping(value = "/add_material", method = RequestMethod.POST)
    public ModelAndView add_material_post(@ModelAttribute("material") Materijal materijal) {
        MaterijalService materijalS = new MaterijalService();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("");
            List<Materijal> materijali = new ArrayList<>();
            materijal.setJedinicaMere(materijal.getJm().toString());
            materijalS.dodajMaterijal(materijal);
            materijali = materijalS.ucitajMaterijale();
            mv.setViewName("all_materials");
            mv.addObject("listaMaterijala", materijali);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("add_material");
            mv.addObject("error", ex.getMessage());
            return mv;
        }
    }

    @RequestMapping(value = "/remove_material/{id}", method = RequestMethod.GET)
    public ModelAndView remove_material(@PathVariable String id) {
        MaterijalService materijalS = new MaterijalService();
        Materijal materijal = new Materijal();
        try {
            materijal = materijalS.pronadjiMaterijal(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("remove_material", "material", materijal);

        return mv;
    }

    @RequestMapping(value = "/remove_material/{id}", method = RequestMethod.POST)
    public ModelAndView remove_material_post(@PathVariable String id, @ModelAttribute("material") Materijal materijal) {
        MaterijalService materijalS = new MaterijalService();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("");
            List<Materijal> materijali = new ArrayList<>();
            materijalS.obrisiMaterijal(id);
            materijali = materijalS.ucitajMaterijale();
            mv.setViewName("all_materials");
            mv.addObject("listaMaterijala", materijali);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("remove_material");
            mv.addObject("error", ex.getMessage());
            return mv;
        }
    }

    @RequestMapping(value = "/find_material/{id}", method = RequestMethod.GET)
    public ModelAndView find_material(@PathVariable String id) {
        MaterijalService materijalS = new MaterijalService();
        Materijal materijal = new Materijal();
        try {
            materijal = materijalS.pronadjiMaterijal(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("find_material");
        mv.addObject("material", materijal);
        return mv;
    }

    @RequestMapping(value = "/update_material/{id}", method = RequestMethod.GET)
    public ModelAndView update_material_get(@PathVariable String id) {
        MaterijalService materijalS = new MaterijalService();
        Materijal materijal = new Materijal();
        try {
            materijal = materijalS.pronadjiMaterijal(id);
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_material", "material", materijal);
        mv.addObject("jedniceMere", JedinicaMere.values());
        return mv;
    }

    @RequestMapping(value = "/update_material/{id}", method = RequestMethod.POST)
    public ModelAndView update_material_post(@ModelAttribute("material") Materijal materijal, @PathVariable String id) {
        MaterijalService materijalS = new MaterijalService();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("");
            List<Materijal> materijali = new ArrayList<>();
            materijal.setJedinicaMere(materijal.getJm().toString());
            materijal.setSifraMaterijala(id);
            materijalS.zapamtiMaterijal(materijal);
            materijali = materijalS.ucitajMaterijale();
            mv.setViewName("all_materials");
            mv.addObject("listaMaterijala", materijali);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("remove_material");
            mv.addObject("error", ex.getMessage());
            return mv;
        }
    }
}

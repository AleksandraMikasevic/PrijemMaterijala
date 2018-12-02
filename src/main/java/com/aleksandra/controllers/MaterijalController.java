/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.domen.JedinicaMere;
import com.aleksandra.domen.Materijal;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Stavkaprijemnice;
import com.aleksandra.service.MaterijalService;
import com.aleksandra.service.PrijemnicaService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("/material")
@Scope("session")
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

    @RequestMapping("/all_materials_graph")
    public ModelAndView all_materials_graph() {
        ModelAndView mv = new ModelAndView("all_materials_graph");
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_materials_json_graph", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> mat_json_graph() {
        MaterijalService materijal = new MaterijalService();
        PrijemnicaService prijemnica = new PrijemnicaService();
        List<Prijemnica> prijemnice = new ArrayList<>();
        List<Materijal> materijali = new ArrayList<>();
        try {
            materijali = materijal.ucitajMaterijale();
            prijemnice = prijemnica.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<Stavkaprijemnice> stavke = new ArrayList<>();
        for (Prijemnica prijemnica1 : prijemnice) {
            stavke.addAll(prijemnica1.getStavkaprijemniceCollection());
        }
        System.out.println("duzina stavkiii: " + stavke.size());
        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        List<String> listaMat = new ArrayList<>();
        List<Double> listaKol = new ArrayList<>();
        for (Materijal materijal1 : materijali) {
            double ukupno = 0;
            System.out.println("sifra materijala materijal: " + materijal1.getSifraMaterijala());
            for (Stavkaprijemnice stavkaprijemnice : stavke) {
                System.out.println("sifra materijala stavke: "+stavkaprijemnice.getSifraMaterijala().getSifraMaterijala());
                System.out.println("sandraaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa1111");
                if (stavkaprijemnice.getSifraMaterijala().getSifraMaterijala().equals(materijal1.getSifraMaterijala())) {
                    ukupno = ukupno + stavkaprijemnice.getKolicina();
                    System.out.println("sandraaaaaaaa; ukupno: " + ukupno);
                }
            }
            //ovde dodas materijal i broj ukupno za njega
            listaMat.add(materijal1.getNazivMaterijala());
            listaKol.add(ukupno);
        }
        Map<String, Object> json = new HashMap();
        json.put("materijali", listaMat);
        json.put("kolicine", listaKol);
        return json;
    }

    @ResponseBody
    @RequestMapping(value = "/all_materials_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Materijal> mat_json() {
        MaterijalService materijalService = new MaterijalService();
        List<Materijal> materijali = new ArrayList<>();
        try {
            materijali = materijalService.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return materijali;
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

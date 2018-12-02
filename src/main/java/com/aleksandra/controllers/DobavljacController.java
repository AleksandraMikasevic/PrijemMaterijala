/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.domen.Dobavljac;
import com.aleksandra.domen.Materijal;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Stavkaprijemnice;
import com.aleksandra.service.DobavljacService;
import com.aleksandra.service.MaterijalService;
import com.aleksandra.service.PrijemnicaService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
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
@RequestMapping("/supplier")
public class DobavljacController {

    @RequestMapping("/all_suppliers")
    public ModelAndView all_materials() {
        DobavljacService dobavljacService = new DobavljacService();
        List<Dobavljac> dobavljaci = new ArrayList<>();
        try {
            dobavljaci = dobavljacService.ucitajDobavljace();
        } catch (Exception ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ModelAndView mv = new ModelAndView("all_suppliers");
        mv.addObject("listaDobavljaca", dobavljaci);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_suppliers_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Dobavljac> dobavljaci_json() {
        DobavljacService dobavljacService = new DobavljacService();
        List<Dobavljac> dobavljaci = new ArrayList<>();
        try {
            dobavljaci = dobavljacService.ucitajDobavljace();
        } catch (Exception ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return dobavljaci;
    }

    @RequestMapping(value = "/add_supplier", method = RequestMethod.GET)
    public ModelAndView add_material_get() {
        ModelAndView mv = new ModelAndView("add_supplier", "supplier", new Dobavljac());
        return mv;
    }

    @RequestMapping(value = "/add_supplier", method = RequestMethod.POST)
    public ModelAndView add_material_post(@ModelAttribute("supplier") Dobavljac dobavljac) {
        DobavljacService dobavljacS = new DobavljacService();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("");
            List<Dobavljac> dobavljaci = new ArrayList<>();
            dobavljacS.dodajDobavljac(dobavljac);
            dobavljaci = dobavljacS.ucitajDobavljace();
            mv.setViewName("all_suppliers");
            mv.addObject("listaDobavljaca", dobavljaci);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("add_suppler");
            mv.addObject("error", ex.getMessage());
            return mv;
        }
    }

    @RequestMapping(value = "/remove_supplier/{id}", method = RequestMethod.GET)
    public ModelAndView remove_material(@PathVariable String id) {
        DobavljacService dobavljacS = new DobavljacService();
        Dobavljac dobavljac = new Dobavljac();
        try {
            dobavljac = dobavljacS.pronadjiDobavljaca(id);
        } catch (Exception ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("remove_supplier", "supplier", dobavljac);

        return mv;
    }

    @RequestMapping(value = "/remove_supplier/{id}", method = RequestMethod.POST)
    public ModelAndView remove_material_post(@PathVariable String id, @ModelAttribute("supplier") Dobavljac dobavljac) {
        DobavljacService dobavljacS = new DobavljacService();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("");
            List<Dobavljac> dobavljaci = new ArrayList<>();
            dobavljacS.obrisiDobavljaca(id);
            dobavljaci = dobavljacS.ucitajDobavljace();
            mv.setViewName("all_suppliers");
            mv.addObject("listaDobavljaca", dobavljaci);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("remove_supplier");
            mv.addObject("error", ex.getMessage());
            return mv;
        }
    }

    @RequestMapping(value = "/find_supplier/{id}", method = RequestMethod.GET)
    public ModelAndView find_material(@PathVariable String id) {
        DobavljacService dobavljacS = new DobavljacService();
        Dobavljac dobavljac = new Dobavljac();
        try {
            dobavljac = dobavljacS.pronadjiDobavljaca(id);
        } catch (Exception ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("find_supplier");
        mv.addObject("supplier", dobavljac);
        return mv;
    }
     @ResponseBody
    @RequestMapping(value = "/all_suppliers_json_graph", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> suppliers_json_graph() {
        DobavljacService dobavljac = new DobavljacService();
        PrijemnicaService prijemnica = new PrijemnicaService();
        List<Prijemnica> prijemnice = new ArrayList<>();
        List<Dobavljac> dobavljaci = new ArrayList<>();
        try {
            dobavljaci = dobavljac.ucitajDobavljace();
            prijemnice = prijemnica.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        JSONArray jsonArray1 = new JSONArray();
        JSONArray jsonArray2 = new JSONArray();
        List<String> listaDob = new ArrayList<>();
        List<Integer> listaNar = new ArrayList<>();
        for (Dobavljac dobavljac1 : dobavljaci) {
            int ukupno = 0;
            for (Prijemnica prijemnica1 : prijemnice) {
                if (prijemnica1.getPib().getPib().equals(dobavljac1.getPib())) {
                    ukupno++;
                }
            }
            //ovde dodas materijal i broj ukupno za njega
            listaDob.add(dobavljac1.getNaziv());
            listaNar.add(ukupno);
        }
        Map<String, Object> json = new HashMap();
        json.put("dobavljaci", listaDob);
        json.put("narudzbine", listaNar);
        return json;
    }
    @RequestMapping(value = "/update_supplier/{id}", method = RequestMethod.GET)
    public ModelAndView update_supplier_get(@PathVariable String id) {
        DobavljacService dobavljacS = new DobavljacService();
        Dobavljac dobavljac = new Dobavljac();
        try {
            dobavljac = dobavljacS.pronadjiDobavljaca(id);
        } catch (Exception ex) {
            Logger.getLogger(DobavljacController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_supplier", "supplier", dobavljac);
        return mv;
    }

    @RequestMapping(value = "/update_supplier/{id}", method = RequestMethod.POST)
    public ModelAndView update_supplier_post(@ModelAttribute("supplier") Dobavljac dobavljac, @PathVariable String id) {
        DobavljacService dobavljacS = new DobavljacService();
        ModelAndView mv = new ModelAndView();
        try {
            mv.setViewName("");
            List<Dobavljac> dobavljaci = new ArrayList<>();
            dobavljac.setPib(id);
            dobavljacS.zapamtiDobavljaca(dobavljac);
            dobavljaci = dobavljacS.ucitajDobavljace();
            mv.setViewName("all_suppliers");
            mv.addObject("listaDobavljaca", dobavljaci);
            return mv;
        } catch (Exception ex) {
            mv.setViewName("update_supplier");
            mv.addObject("error", ex.getMessage());
            return mv;
        }
    }

}

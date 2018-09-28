/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.controllers;

import com.aleksandra.domen.Vagarskapotvrda;
import com.aleksandra.service.VagarskaPotvrdaService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author User
 */
@Controller
@RequestMapping("vagarska_potvrda")
public class VagarskaPotvrdaController {

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET)
    public String combobox(ModelMap model) {
        VagarskaPotvrdaService vpService = new VagarskaPotvrdaService();

        List<Vagarskapotvrda> vagarskaPotvrdaLista = new ArrayList<>();
        try {
            vagarskaPotvrdaLista = vpService.ucitajVagarskePotvrde();
        } catch (Exception ex) {
            Logger.getLogger(VagarskaPotvrdaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new JSONArray(vagarskaPotvrdaLista).toString();
    }
}

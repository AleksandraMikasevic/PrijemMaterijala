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
import com.aleksandra.domen.StavkaprijemnicePK;
import com.aleksandra.domen.Vagarskapotvrda;
import com.aleksandra.domen.Zaposleni;
import com.aleksandra.service.DobavljacService;
import com.aleksandra.service.MaterijalService;
import com.aleksandra.service.PrijemnicaService;
import com.aleksandra.service.VagarskaPotvrdaService;
import com.aleksandra.service.ZaposleniService;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.annotation.Scope;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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
@RequestMapping("/goods_received_note")
@Scope("session")
public class PrijemnicaController {

    Prijemnica prijemnica = new Prijemnica();

    @RequestMapping("/all_goods_received_notes")
    public ModelAndView all_goods_received_notes() {

        prijemnica = new Prijemnica();
        PrijemnicaService prijemnicaService = new PrijemnicaService();
        List<Prijemnica> prijemnice = new ArrayList<>();
        try {
            prijemnice = prijemnicaService.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("all_goods_received_notes");
        mv.addObject("listaPrijemnica", prijemnice);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/all_goods_received_note_json", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prijemnica> all_goods_received_notes_json() {
        prijemnica = new Prijemnica();
        PrijemnicaService prijemnicaService = new PrijemnicaService();
        List<Prijemnica> prijemnice = new ArrayList<>();
        try {
            prijemnice = prijemnicaService.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return prijemnice;
    }

    @ResponseBody
    @RequestMapping(value = "/items_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Stavkaprijemnice> items_json() {
        return prijemnica.getStavkaprijemniceCollection();
    }

    @ResponseBody
    @RequestMapping(value = "/json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Prijemnica> mat_json() {
        PrijemnicaService prijemnicaService = new PrijemnicaService();
        List<Prijemnica> prijemnice = new ArrayList<>();
        try {
            prijemnice = prijemnicaService.ucitajPrijemnice();
        } catch (Exception ex) {
            Logger.getLogger(MaterijalController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return prijemnice;
    }

    @RequestMapping(value = "/add_goods_received_note", method = RequestMethod.GET)
    public ModelAndView add_goods_received_note_get() {
        prijemnica = new Prijemnica();
        ModelAndView mv = new ModelAndView("add_goods_received_note");
        PrijemnicaService prijemnicaService = new PrijemnicaService();
        try {
            int brojPrijemnice = prijemnicaService.vratiBrojPrijemnice();
            System.out.println(brojPrijemnice + "  -----------------------------------broj prijemnice");
            prijemnica.setBrojPrijemnice(brojPrijemnice);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("grcn", prijemnica);
        DobavljacService dobavljacS = new DobavljacService();
        VagarskaPotvrdaService vpS = new VagarskaPotvrdaService();
        ZaposleniService zaposleniS = new ZaposleniService();
        List<Vagarskapotvrda> listaVagarskihPotvrda = new ArrayList<>();
        List<Zaposleni> listaZaposlenih = new ArrayList<>();
        List<Dobavljac> listaDobavljaca = new ArrayList<>();
        try {
            listaVagarskihPotvrda = vpS.pronadjiMoguceVagarskePotvrde();
            listaZaposlenih = zaposleniS.ucitajZaposlene();
            listaDobavljaca = dobavljacS.ucitajDobavljace();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaVagarskihPotvrda", listaVagarskihPotvrda);
        mv.addObject("listaZaposlenih", listaZaposlenih);
        mv.addObject("listaDobavljaca", listaDobavljaca);
        return mv;
    }

    @RequestMapping(value = "/add_goods_received_note", method = RequestMethod.POST)
    public String add_goods_received_note() {
        izracunajUkupno();
        PrijemnicaService prijemnicaS = new PrijemnicaService();
        try {
            prijemnicaS.dodajPrijemnicu(prijemnica);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/goods_received_note/all_goods_received_notes";
    }

    @RequestMapping(value = "/add_goods_received_note_info", method = RequestMethod.POST)
    public String add_goods_received_note_info(@ModelAttribute("grcn") Prijemnica prijemnicaM) {
        prijemnica.setDatum(prijemnicaM.getDatum());
        System.out.println(prijemnicaM.getDatum());
        prijemnica.setBrojVagarskePotvrde(prijemnicaM.getBrojVagarskePotvrde());
        prijemnica.setJmbg(prijemnicaM.getJmbg());
        prijemnica.setPib(prijemnicaM.getPib());
        return "redirect:/goods_received_note/add_goods_received_items";
    }

    @RequestMapping(value = "/change_goods_received_note_info", method = RequestMethod.POST)
    public String change_goods_received_note_info(@ModelAttribute("grcn") Prijemnica prijemnicaM) {
        prijemnica.setDatum(prijemnicaM.getDatum());
        prijemnica.setBrojVagarskePotvrde(prijemnicaM.getBrojVagarskePotvrde());
        prijemnica.setJmbg(prijemnicaM.getJmbg());

        return "redirect:/goods_received_note/change_goods_received_items";
    }

    @RequestMapping(value = "/change_goods_received_items", method = RequestMethod.GET)
    public ModelAndView change_goods_received_items() {
        ModelAndView mv = new ModelAndView("change_goods_received_items");
        mv.addObject("grcn", prijemnica);
        MaterijalService materijalS = new MaterijalService();
        List<Materijal> listaMaterijala = new ArrayList<>();
        try {
            listaMaterijala = materijalS.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaMaterijala", listaMaterijala);
        return mv;
    }

    @RequestMapping(value = "/add_goods_received_items", method = RequestMethod.GET)
    public ModelAndView add_goods_received_items() {
        ModelAndView mv = new ModelAndView("add_goods_received_items");
        mv.addObject("grcn", prijemnica);
        MaterijalService materijalS = new MaterijalService();
        List<Materijal> listaMaterijala = new ArrayList<>();
        try {
            listaMaterijala = materijalS.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaMaterijala", listaMaterijala);
        return mv;
    }

    @ResponseBody
    @RequestMapping(value = "/add_goods_received_note_item/{rbr}/{sifraMaterijala}/{kolicina}", method = RequestMethod.GET)
    public String add_goods_received_note_item(@PathVariable int rbr, @PathVariable String sifraMaterijala, @PathVariable double kolicina) {
        try {
            Stavkaprijemnice stavka = new Stavkaprijemnice();
            if (prijemnica.getStavkaprijemniceCollection().size() == 0) {
                stavka = new Stavkaprijemnice(new StavkaprijemnicePK(prijemnica.getBrojPrijemnice(), 1));
                stavka.setRedniBroj(1);
            } else {
                stavka = new Stavkaprijemnice(new StavkaprijemnicePK(prijemnica.getBrojPrijemnice(), prijemnica.getStavkaprijemniceCollection().get(prijemnica.getStavkaprijemniceCollection().size() - 1).getStavkaprijemnicePK().getBrojStavke() + 1));
                stavka.setRedniBroj(rbr);
            }
            stavka.setSifraMaterijala((new MaterijalService().pronadjiMaterijal(sifraMaterijala)));
            stavka.setPrijemnica(prijemnica);
            stavka.setKolicina(kolicina);
            prijemnica.getStavkaprijemniceCollection().add(stavka);
            String s = " <tr>"
                    + "<th scope=\"row\">" + rbr + "</th>"
                    + "<td>" + kolicina + "</td>"
                    + "<td>" + sifraMaterijala + "</td>"
                    + "<td>"
                    + "<a href=\'/NJProjekatFED/goods_received_note/goods_received_note_item_info/" + rbr + "'\" class=\"btn btn-primary\"><i class=\"fa fa-cogs\"></i></a>"
                    + "</td>"
                    + "</tr>";
            return s;
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/add_item/{sifraMaterijala}/{kolicina}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Stavkaprijemnice add_item(@PathVariable String sifraMaterijala, @PathVariable double kolicina) {
        Stavkaprijemnice stavka = new Stavkaprijemnice();
        try {
            if (prijemnica.getStavkaprijemniceCollection().isEmpty()) {
                stavka = new Stavkaprijemnice(new StavkaprijemnicePK(prijemnica.getBrojPrijemnice(), 1));
                stavka.setRedniBroj(1);
            } else {
                stavka = new Stavkaprijemnice(new StavkaprijemnicePK(prijemnica.getBrojPrijemnice(), prijemnica.getStavkaprijemniceCollection().get(prijemnica.getStavkaprijemniceCollection().size() - 1).getStavkaprijemnicePK().getBrojStavke() + 1));
                stavka.setRedniBroj(prijemnica.getStavkaprijemniceCollection().size() + 1);
            }
            stavka.setSifraMaterijala((new MaterijalService().pronadjiMaterijal(sifraMaterijala)));
            stavka.setPrijemnica(prijemnica);
            stavka.setKolicina(kolicina);
            prijemnica.getStavkaprijemniceCollection().add(stavka);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stavka;
    }

    @ResponseBody
    @RequestMapping(value = "/update_item/{rbr}/{sifraMaterijala}/{kolicina}", method = RequestMethod.GET)
    public String update_item(@PathVariable int rbr, @PathVariable String sifraMaterijala, @PathVariable double kolicina) {
        Stavkaprijemnice stv = new Stavkaprijemnice();
        for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
            if (stavkaprijemnice.getStavkaprijemnicePK().getBrojStavke() == rbr) {
                stv = stavkaprijemnice;
            }
        }
        stv.setKolicina(kolicina);
        stv.getSifraMaterijala().setSifraMaterijala(sifraMaterijala);
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/remove_item/{brojStavke}", method = RequestMethod.GET)
    public Stavkaprijemnice remove_item(@PathVariable int brojStavke) {
        Stavkaprijemnice stv = new Stavkaprijemnice();
        for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
            if (stavkaprijemnice.getStavkaprijemnicePK().getBrojStavke() == brojStavke) {
                stv = stavkaprijemnice;
            }
        }
        prijemnica.getStavkaprijemniceCollection().remove(stv);
        srediRbr();
        return null;
    }

    @ResponseBody
    @RequestMapping(value = "/item_json", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Stavkaprijemnice item_json() {
        Stavkaprijemnice stavka = new Stavkaprijemnice();
        stavka.setKolicina(100.00);
        stavka.setRedniBroj(1);
        Materijal materijal = new Materijal();
        materijal.setCena(100.00);
        materijal.setJedinicaMere("cm");
        materijal.setNazivMaterijala("naziv");
        materijal.setSifraMaterijala("sifra");
        stavka.setSifraMaterijala(materijal);
        return stavka;
    }

    @RequestMapping(value = "/goods_received_note_item_info/{rbr}", method = RequestMethod.GET)
    public ModelAndView goods_received_note_item_info(@PathVariable int rbr) {
        ModelAndView mv = new ModelAndView("goods_received_note_item_info");
        Stavkaprijemnice stavka = prijemnica.getStavkaprijemniceCollection().get(rbr - 1);
        MaterijalService materijalS = new MaterijalService();
        List<Materijal> listaMaterijala = new ArrayList<>();
        try {
            listaMaterijala = materijalS.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaMaterijala", listaMaterijala);
        mv.addObject("stavka", stavka);
        mv.addObject("rbr", rbr);
        return mv;
    }

    @RequestMapping(value = "/change_goods_received_note_item_info/{rbr}", method = RequestMethod.GET)
    public ModelAndView change_goods_received_note_item_info(@PathVariable int rbr) {
        ModelAndView mv = new ModelAndView("change_goods_received_note_item_info");

        Stavkaprijemnice stavka = pronadjiStavku(rbr);
        MaterijalService materijalS = new MaterijalService();
        List<Materijal> listaMaterijala = new ArrayList<>();
        try {
            listaMaterijala = materijalS.ucitajMaterijale();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        mv.addObject("listaMaterijala", listaMaterijala);
        mv.addObject("stavka", stavka);
        mv.addObject("rbr", rbr);
        return mv;
    }

    @RequestMapping(value = "/remove_goods_received_note_item_l/{rbr}", method = RequestMethod.GET)
    public String remove_goods_received_note_item(@PathVariable int rbr) {
        Stavkaprijemnice stavka = prijemnica.getStavkaprijemniceCollection().get(rbr - 1);
        prijemnica.getStavkaprijemniceCollection().remove(stavka);
        srediRbr();
        return "redirect:/goods_received_note/add_goods_received_items";
    }

    @RequestMapping(value = "/remove_update_goods_received_note_item_l/{rbr}", method = RequestMethod.GET)
    public String remove_update_goods_received_note_item(@PathVariable int rbr) {
        Stavkaprijemnice stavka = pronadjiStavku(rbr);
        prijemnica.getStavkaprijemniceCollection().remove(stavka);
        srediRbr();
        return "redirect:/goods_received_note/change_goods_received_items";
    }

    @RequestMapping(value = "/update_update_goods_received_note_item/{rbr}", method = RequestMethod.POST)
    public String update_update_goods_received_note_item(@PathVariable int rbr, @ModelAttribute("stavka") Stavkaprijemnice stavka) {
        Stavkaprijemnice stv = pronadjiStavku(rbr);
        stv.setKolicina(stavka.getKolicina());
        stv.getSifraMaterijala().setSifraMaterijala(stavka.getSifraMaterijala().getSifraMaterijala());
        return "redirect:/goods_received_note/change_goods_received_items";
    }

    @RequestMapping(value = "/update_goods_received_note_item/{rbr}", method = RequestMethod.POST)
    public String update_goods_received_note_item(@PathVariable int rbr, @ModelAttribute("stavka") Stavkaprijemnice stavka) {
        Stavkaprijemnice stv = prijemnica.getStavkaprijemniceCollection().get(rbr - 1);
        stv.setKolicina(stavka.getKolicina());
        stv.getSifraMaterijala().setSifraMaterijala(stavka.getSifraMaterijala().getSifraMaterijala());
        return "redirect:/goods_received_note/add_goods_received_items";
    }

    @RequestMapping(value = "/remove_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView remove_goods_received_note(@PathVariable int id) {
        PrijemnicaService prijemnicaS = new PrijemnicaService();
        try {
            prijemnica = prijemnicaS.pronadjiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("remove_goods_received_note", "grcn", prijemnica);

        return mv;
    }

    @RequestMapping(value = "/remove_goods_received_note/{id}", method = RequestMethod.POST)
    public String remove_goods_received_note_post(@PathVariable int id) {
        PrijemnicaService prijemnicaS = new PrijemnicaService();
        try {
            prijemnicaS.obrisiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "redirect:/goods_received_note/all_goods_received_notes";
    }

    @RequestMapping(value = "/find_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView find_goods_received_note(@PathVariable int id) {
        PrijemnicaService prijemnicaS = new PrijemnicaService();
        try {
            prijemnica = prijemnicaS.pronadjiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModelAndView mv = new ModelAndView("find_goods_received_note", "grcn", prijemnica);

        return mv;
    }

    @RequestMapping(value = "/update_goods_received_note/{id}", method = RequestMethod.GET)
    public ModelAndView update_goods_received_note(@PathVariable int id) {
        PrijemnicaService prijemnicaS = new PrijemnicaService();
        prijemnica = new Prijemnica();
        try {
            prijemnica = prijemnicaS.pronadjiPrijemnicu(id);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        VagarskaPotvrdaService vpS = new VagarskaPotvrdaService();
        ZaposleniService zaposleniS = new ZaposleniService();
        DobavljacService dobavljacS = new DobavljacService();
        List<Vagarskapotvrda> listaVagarskihPotvrda = new ArrayList<>();
        List<Zaposleni> listaZaposlenih = new ArrayList<>();
        List<Dobavljac> listaDobavljaca = new ArrayList<>();
        try {
            listaDobavljaca = dobavljacS.ucitajDobavljace();
            listaVagarskihPotvrda = vpS.pronadjiMoguceVagarskePotvrde();
            listaZaposlenih = zaposleniS.ucitajZaposlene();
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        ModelAndView mv = new ModelAndView("update_goods_received_note", "grcn", prijemnica);
        mv.addObject("listaVagarskihPotvrda", listaVagarskihPotvrda);
        mv.addObject("listaZaposlenih", listaZaposlenih);
        mv.addObject("listaDobavljaca", listaDobavljaca);
        return mv;
    }

    @RequestMapping(value = "/update_goods_received_note", method = RequestMethod.POST)
    public String update_goods_received_note_post(@ModelAttribute("goodsReceivedNote") Prijemnica prijemnicaM) {
        PrijemnicaService prijemnicaS = new PrijemnicaService();
        try {
            izracunajUkupno();
            prijemnicaS.zapamtiPrijemnicu(prijemnica);
        } catch (Exception ex) {
            Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/goods_received_note/all_goods_received_notes";
    }

    private void srediRbr() {
        for (int i = 0; i < prijemnica.getStavkaprijemniceCollection().size(); i++) {
            prijemnica.getStavkaprijemniceCollection().get(i).setRedniBroj(i + 1);
        }
    }

    private void izracunajUkupno() {
        double ukupno = 0;
        double pdv = 0;
        for (Stavkaprijemnice stavka : prijemnica.getStavkaprijemniceCollection()) {
            try {
                ukupno = ukupno + (stavka.getKolicina() * stavka.getSifraMaterijala().getCena());
                pdv = pdv + (stavka.getKolicina() * stavka.getSifraMaterijala().getCena() * stavka.getSifraMaterijala().getPdv() * 0.01);
            } catch (Exception ex) {
                Logger.getLogger(PrijemnicaController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        prijemnica.setUkupanPDV(pdv);
        prijemnica.setUkupno(ukupno);
        prijemnica.setUkupnoSaPDV(ukupno+pdv);
    }



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        sdf.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
    }

    private Stavkaprijemnice pronadjiStavku(int rbr) {
        for (Stavkaprijemnice stavkaprijemnice : prijemnica.getStavkaprijemniceCollection()) {
            if (stavkaprijemnice.getStavkaprijemnicePK().getBrojStavke() == rbr) {
                return stavkaprijemnice;
            }
        }
        return null;
    }
}

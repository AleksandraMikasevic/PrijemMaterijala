/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.implementation.PrijemnicaDAO;
import com.aleksandra.dao.implementation.VagarskaPotvrdaDAO;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Vagarskapotvrda;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author User
 */
public class VagarskaPotvrdaService implements IVagarskaPotvrdaService {

    @Override
    public void dodajVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) throws Exception {
        VagarskaPotvrdaDAO vpDAO = new VagarskaPotvrdaDAO();
        vpDAO.dodajVagarskuPotvrdu(vagarskaPotvrda);
    }

    @Override
    public List<Vagarskapotvrda> ucitajVagarskePotvrde() throws Exception {
        VagarskaPotvrdaDAO vpDAO = new VagarskaPotvrdaDAO();
        List<Vagarskapotvrda> vagarskePotvrde = vpDAO.pronadjiSveVagarskePotvrde();
        return vagarskePotvrde;
    }

    @Override
    public Vagarskapotvrda pronadjiVagarskuPotvrdu(int vagarskaPotvrdaID) throws Exception {
        VagarskaPotvrdaDAO vpDAO = new VagarskaPotvrdaDAO();
        Vagarskapotvrda vp = vpDAO.pronadjiVagarskuPotvrdu(vagarskaPotvrdaID);
        return vp;
    }

    @Override
    public void zapamtiVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) throws Exception {
        VagarskaPotvrdaDAO vpDAO = new VagarskaPotvrdaDAO();
        vpDAO.zapamtiVagarskuPotvrdu(vagarskaPotvrda);
    }

    @Override
    public List<Vagarskapotvrda> pronadjiMoguceVagarskePotvrde() throws Exception {
        VagarskaPotvrdaDAO vpDAO = new VagarskaPotvrdaDAO();
        List<Vagarskapotvrda> vagarskePotvrde = vpDAO.pronadjiSveVagarskePotvrde();
        List<Vagarskapotvrda> vagarskePotvrdeNeMoguce = new ArrayList<>();
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        List<Prijemnica> prijemnice = prijemnicaDAO.pronadjiSvePrijemnice();
        for (Vagarskapotvrda vagarskapotvrda : vagarskePotvrde) {
            for (Prijemnica prijemnica : prijemnice) {
                if (vagarskapotvrda.getBrojVagarskePotvrde() == prijemnica.getBrojVagarskePotvrde().getBrojVagarskePotvrde()) {
                    vagarskePotvrdeNeMoguce.add(vagarskapotvrda);
                }
            }
        }
        vagarskePotvrde.removeAll(vagarskePotvrdeNeMoguce);
        return vagarskePotvrde;
    }

}

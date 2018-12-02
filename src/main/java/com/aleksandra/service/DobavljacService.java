/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.implementation.DobavljacDAO;
import com.aleksandra.domen.Dobavljac;
import java.util.List;

/**
 *
 * @author User
 */
public class DobavljacService implements IDobavljacService {

    @Override
    public List<Dobavljac> ucitajDobavljace() throws Exception {
        DobavljacDAO dobavljac = new DobavljacDAO();
        List<Dobavljac> dobavljaci = dobavljac.ucitajDobavljace();
        return dobavljaci;
    }

    @Override
    public void dodajDobavljac(Dobavljac dobavljac) throws Exception {
        DobavljacDAO dobavljacDAO = new DobavljacDAO();
        dobavljacDAO.dodajDobavljaca(dobavljac);
    }

    @Override
    public void obrisiDobavljaca(String dobavljacID) throws Exception {
        DobavljacDAO dobavljacDAO = new DobavljacDAO();
        dobavljacDAO.obrisiDobavljaca(dobavljacID);
    }

    @Override
    public Dobavljac pronadjiDobavljaca(String dobavljacID) throws Exception {
        DobavljacDAO dobavljacDAO = new DobavljacDAO();
        Dobavljac dobavljac = dobavljacDAO.pronadjiDobavljaca(dobavljacID);
        return dobavljac;
    }

    @Override
    public void zapamtiDobavljaca(Dobavljac dobavljac) throws Exception {
        DobavljacDAO dobavljacDAO = new DobavljacDAO();
        dobavljacDAO.zapamtiDobavljaca(dobavljac);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.implementation.PrijemnicaDAO;
import com.aleksandra.domen.Prijemnica;
import java.util.Date;
import java.util.List;

/**
 *
 * @author User
 */
public class PrijemnicaService implements IPrijemnicaService {

    @Override
    public void dodajPrijemnicu(Prijemnica prijemnica) throws Exception {
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        prijemnica.setDatumUnosa(new Date());
        prijemnicaDAO.dodajPrijemnicu(prijemnica);
    }

    @Override
    public void obrisiPrijemnicu(int prijemnicaID) throws Exception {
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        prijemnicaDAO.obrisiPrijemnicu(prijemnicaID);
    }

    @Override
    public List<Prijemnica> ucitajPrijemnice() throws Exception {
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        List<Prijemnica> prijemnice = prijemnicaDAO.pronadjiSvePrijemnice();
        return prijemnice;
    }

    @Override
    public Prijemnica pronadjiPrijemnicu(int prijemnicaID) throws Exception {
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        Prijemnica prijemnica = prijemnicaDAO.pronadjiPrijemnicu(prijemnicaID);
        return prijemnica;
    }

    @Override
    public void zapamtiPrijemnicu(Prijemnica prijemnica) throws Exception {
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        prijemnicaDAO.zapamtiPrijemnicu(prijemnica);
    }

    @Override
    public int vratiBrojPrijemnice() throws Exception {
        PrijemnicaDAO prijemnicaDAO = new PrijemnicaDAO();
        return prijemnicaDAO.vratiBrojPrijemnice();
    }

    public List<Prijemnica> ucitajPrijemniceOdDo(int start, int pageLength) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Prijemnica> ucitajPrijemniceTabela(int start, int end, String sortField, String sortDirection) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}

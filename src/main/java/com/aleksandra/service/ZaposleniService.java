/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.implementation.ZaposleniDAO;
import com.aleksandra.domen.Zaposleni;
import java.util.List;

/**
 *
 * @author User
 */
public class ZaposleniService implements IZaposleniService {

    @Override
    public List<Zaposleni> ucitajZaposlene() throws Exception {
        ZaposleniDAO zaposleniDAO = new ZaposleniDAO();
        List<Zaposleni> zaposleni = zaposleniDAO.ucitajZaposlene();
        return zaposleni;
    }

    @Override
    public Zaposleni proveriZaposlenog(String username, String password) throws Exception {
        ZaposleniDAO zaposleniDAO = new ZaposleniDAO();
        List<Zaposleni> zaposleni = zaposleniDAO.proveriZaposlenog(username, password);
        if(zaposleni.size() == 0) {
            throw new Exception("Pogresno ime i/ili lozinka.");
        }
        return zaposleni.get(0);
    }
}

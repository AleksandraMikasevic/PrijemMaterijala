/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.dao.implementation.MaterijalDAO;
import com.aleksandra.domen.Materijal;
import java.util.List;

/**
 *
 * @author User
 */
public class MaterijalService implements IMaterijalService {

    @Override
    public void dodajMaterijal(Materijal materijal) throws Exception {
        MaterijalDAO materijalDao = new MaterijalDAO();
        materijalDao.dodajMaterijal(materijal);
    }

    @Override
    public void obrisiMaterijal(String materijalID) throws Exception {
        MaterijalDAO materijalDao = new MaterijalDAO();
        materijalDao.obrisiMaterijal(materijalID);
    }

    @Override
    public List<Materijal> ucitajMaterijale() throws Exception {
        MaterijalDAO materijal = new MaterijalDAO();
        List<Materijal> materijali = materijal.ucitajMaterijale();
        return materijali;
    }

    @Override
    public Materijal pronadjiMaterijal(String materijalID) throws Exception {
        MaterijalDAO materijal = new MaterijalDAO();
        Materijal mat = materijal.pronadjiMaterijal(materijalID);
        if (mat == null) {
            throw new Exception("Ne postoji trazeni materijal.");
        }
        return mat;
    }

    @Override
    public void zapamtiMaterijal(Materijal materijal) throws Exception {
        MaterijalDAO materijalDao = new MaterijalDAO();
        materijalDao.zapamtiMaterijal(materijal);
    }

}

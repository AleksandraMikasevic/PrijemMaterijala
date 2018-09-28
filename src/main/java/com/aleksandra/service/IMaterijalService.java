/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.service;

import com.aleksandra.domen.Materijal;
import java.util.List;

/**
 *
 * @author User
 */
public interface IMaterijalService {

    public void dodajMaterijal(Materijal materijal) throws Exception;

    public void obrisiMaterijal(String materijalID) throws Exception;

    public List<Materijal> ucitajMaterijale() throws Exception;

    public Materijal pronadjiMaterijal(String materijalID) throws Exception;

    public void zapamtiMaterijal(Materijal materijal) throws Exception;
}

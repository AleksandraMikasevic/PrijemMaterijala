/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Materijal;
import java.util.List;

/**
 *
 * @author User
 */
public interface IMaterijalDAO {

    public void dodajMaterijal(Materijal materijal);

    public void obrisiMaterijal(String materijalID)throws Exception;

    public List<Materijal> ucitajMaterijale();

    public Materijal pronadjiMaterijal(String materijalID);

    public void zapamtiMaterijal(Materijal materijal) throws Exception;
}

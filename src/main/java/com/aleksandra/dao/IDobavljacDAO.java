/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Dobavljac;
import java.util.List;

/**
 *
 * @author User
 */
public interface IDobavljacDAO {

    public void dodajDobavljaca(Dobavljac dobavljac);

    public void obrisiDobavljaca(String dobavljacID) throws Exception;

    public List<Dobavljac> ucitajDobavljace();

    public Dobavljac pronadjiDobavljaca(String dobavljacID);

    public void zapamtiDobavljaca(Dobavljac dobavljac) throws Exception;

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Prijemnica;
import java.util.List;

/**
 *
 * @author User
 */
public interface IPrijemnicaDAO {

    public void dodajPrijemnicu(Prijemnica prijemnica);

    public void obrisiPrijemnicu(int prijemnicaID);

    public Prijemnica pronadjiPrijemnicu(int prijemnicaID);

    public List<Prijemnica> pronadjiSvePrijemnice();

    public void zapamtiPrijemnicu(Prijemnica prijemnica);

    public int vratiBrojPrijemnice();

}

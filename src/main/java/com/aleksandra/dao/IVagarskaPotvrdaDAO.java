/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Vagarskapotvrda;
import java.util.List;

/**
 *
 * @author User
 */
public interface IVagarskaPotvrdaDAO {

    public void dodajVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda);

    public Vagarskapotvrda pronadjiVagarskuPotvrdu(int vagarskaPotvrdaID);

    public List<Vagarskapotvrda> pronadjiSveVagarskePotvrde();

    public void zapamtiVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda);


}

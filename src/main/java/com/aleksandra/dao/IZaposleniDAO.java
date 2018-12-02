/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao;

import com.aleksandra.domen.Zaposleni;
import java.util.List;

/**
 *
 * @author User
 */
public interface IZaposleniDAO {

    public List<Zaposleni> proveriZaposlenog(String korisnickoIme, String lozinka);

    public List<Zaposleni> ucitajZaposlene();
    
        public Zaposleni pronadjiZaposlenog(String username);

}

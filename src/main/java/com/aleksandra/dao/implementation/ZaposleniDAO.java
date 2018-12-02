/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao.implementation;

import com.aleksandra.dao.IZaposleniDAO;
import com.aleksandra.domen.Zaposleni;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author User
 */
@Repository
public class ZaposleniDAO implements IZaposleniDAO {
    @PersistenceContext
    private EntityManager em;

    
    @Override
    public List<Zaposleni> proveriZaposlenog(String korisnickoIme, String lozinka) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        System.out.println(korisnickoIme);
        System.out.println(lozinka);
        Query query = em.createNamedQuery("Zaposleni.findByLogin", Zaposleni.class);
        query.setParameter("korisnickoIme", korisnickoIme);
        query.setParameter("lozinka", lozinka);
        List<Zaposleni> zaposleni = query.getResultList();
        em.close();
        emf.close();
        return zaposleni;
    }

    @Override
    public List<Zaposleni> ucitajZaposlene() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("Zaposleni.findAll", Zaposleni.class);
        List<Zaposleni> zaposleni = query.getResultList();
        em.close();
        emf.close();
        return zaposleni;
    }

    @Override
    public Zaposleni pronadjiZaposlenog(String username) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("Zaposleni.findByKorisnickoIme", Zaposleni.class).setParameter("korisnickoIme", username);
        Zaposleni zaposleni = (Zaposleni) query.getSingleResult();
        em.close();
        emf.close();
        return zaposleni;

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao.implementation;

import com.aleksandra.dao.IPrijemnicaDAO;
import com.aleksandra.domen.IStatus;
import com.aleksandra.domen.Prijemnica;
import com.aleksandra.domen.Stavkaprijemnice;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class PrijemnicaDAO implements IPrijemnicaDAO {

    @Override
    public void dodajPrijemnicu(Prijemnica prijemnica) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(prijemnica);
        for (Stavkaprijemnice stavka : prijemnica.getStavkaprijemniceCollection()) {
            em.persist(stavka);
        }
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public void obrisiPrijemnicu(int prijemnicaID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Prijemnica prijemnica = em.find(Prijemnica.class, prijemnicaID);
        if (prijemnica == null) {
            System.out.println("Ne postoji trazena prijemnica za brisanje.");
            return;
        }
        em.getTransaction().begin();
        em.remove(prijemnica);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public Prijemnica pronadjiPrijemnicu(int prijemnicaID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Prijemnica prijemnica = em.find(Prijemnica.class, prijemnicaID);
        if (prijemnica == null) {
            System.out.println("Ne postoji trazena prijemnica.");
            return null;
        }
        return prijemnica;
    }

    @Override
    public List<Prijemnica> pronadjiSvePrijemnice() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Prijemnica.findAll", Prijemnica.class);
        List<Prijemnica> prijemnice = query.getResultList();
        em.close();
        emf.close();
        return prijemnice;
    }

    @Override
    public void zapamtiPrijemnicu(Prijemnica prijemnica) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Prijemnica prijemnicaProvera = em.find(Prijemnica.class, prijemnica.getBrojPrijemnice());
        if (prijemnicaProvera == null) {
            System.out.println("Ne postoji trazena prijemnica za izmenu.");
            return;
        }
        em.getTransaction().begin();
        em.merge(prijemnica);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public int vratiBrojPrijemnice() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Prijemnica.getNumber", Integer.class);
        if (query.getSingleResult() == null) {
            return 1;
        } else {
            int broj = (int) query.getSingleResult();
            System.out.println("BROJ " + broj);
            return ++broj;
        }
    }

}

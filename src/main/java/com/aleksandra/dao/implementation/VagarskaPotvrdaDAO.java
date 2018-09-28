/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao.implementation;

import com.aleksandra.dao.IVagarskaPotvrdaDAO;
import com.aleksandra.domen.Vagarskapotvrda;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author User
 */
public class VagarskaPotvrdaDAO implements IVagarskaPotvrdaDAO{

    @Override
    public void dodajVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(vagarskaPotvrda);
        em.getTransaction().commit();
        em.close();
        emf.close();    }

    @Override
    public Vagarskapotvrda pronadjiVagarskuPotvrdu(int vagarskaPotvrdaID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Vagarskapotvrda vagarskaPotvrda = em.find(Vagarskapotvrda.class, vagarskaPotvrdaID);
        if (vagarskaPotvrda == null) {
            System.out.println("Ne postoji trazena vagarskaPotvrda.");
            return null;
        }
        return vagarskaPotvrda;    }

    @Override
    public List<Vagarskapotvrda> pronadjiSveVagarskePotvrde() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Query query = em.createNamedQuery("Vagarskapotvrda.findAll", Vagarskapotvrda.class);
        List<Vagarskapotvrda> vagarskePotvrde = query.getResultList();
        em.close();
        emf.close();
        return vagarskePotvrde;    }

    @Override
    public void zapamtiVagarskuPotvrdu(Vagarskapotvrda vagarskaPotvrda) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        EntityManager em = emf.createEntityManager();
        Vagarskapotvrda vagarskaPotvrdaProvera = em.find(Vagarskapotvrda.class, vagarskaPotvrda.getBrojVagarskePotvrde());
        if (vagarskaPotvrdaProvera == null) {
            System.out.println("Ne postoji trazena vagarska potvrda za izmenu.");
            return;
        }
        em.getTransaction().begin();
        em.merge(vagarskaPotvrda);
        em.getTransaction().commit();
        em.close();
        emf.close();    }
    
}

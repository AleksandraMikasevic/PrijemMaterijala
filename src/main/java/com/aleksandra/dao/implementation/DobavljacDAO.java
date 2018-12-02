/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao.implementation;

import com.aleksandra.dao.IDobavljacDAO;
import com.aleksandra.domen.Dobavljac;
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
public class DobavljacDAO implements IDobavljacDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Dobavljac> ucitajDobavljace() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("Dobavljac.findAll", Dobavljac.class);
        List<Dobavljac> dobavljaci = query.getResultList();
        em.close();
        emf.close();
        return dobavljaci;
    }

    @Override
    public void dodajDobavljaca(Dobavljac dobavljac) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(dobavljac);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public void obrisiDobavljaca(String dobavljacID) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Dobavljac dobavljac = em.find(Dobavljac.class, dobavljacID);
        if (dobavljac == null) {
            throw new Exception("Ne postoji trazeni dobavljac za brisanje.");
        }
        em.getTransaction().begin();
        em.remove(dobavljac);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public Dobavljac pronadjiDobavljaca(String dobavljacID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Dobavljac dobavljac = em.find(Dobavljac.class, dobavljacID);
        if (dobavljac == null) {
            System.out.println("Ne postoji trazeni dobavljac.");
            return null;
        }
        return dobavljac;

    }

    @Override
    public void zapamtiDobavljaca(Dobavljac dobavljac) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Dobavljac dobavljacProvera = em.find(Dobavljac.class, dobavljac.getPib());
        if (dobavljacProvera == null) {
            throw new Exception("Ne postoji trazen dobavljac za izmenu.");
        }
        em.getTransaction().begin();
        em.merge(dobavljac);
        em.getTransaction().commit();
        em.close();
        emf.close();

    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.dao.implementation;

import com.aleksandra.dao.IMaterijalDAO;
import com.aleksandra.domen.Materijal;
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
public class MaterijalDAO implements IMaterijalDAO {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void dodajMaterijal(Materijal materijal) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(materijal);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public void obrisiMaterijal(String materijalID) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Materijal materijal = em.find(Materijal.class, materijalID);
        if (materijal == null) {
            throw new Exception("Ne postoji trazeni materijal za brisanje.");
        }
        em.getTransaction().begin();
        em.remove(materijal);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

    @Override
    public List<Materijal> ucitajMaterijale() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Query query = em.createNamedQuery("Materijal.findAll", Materijal.class);
        List<Materijal> materijali = query.getResultList();
        em.close();
        emf.close();
        return materijali;
    }

    @Override
    public Materijal pronadjiMaterijal(String materijalID) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Materijal materijal = em.find(Materijal.class, materijalID);
        if (materijal == null) {
            System.out.println("Ne postoji trazeni materijal.");
            return null;
        }
        return materijal;
    }

    @Override
    public void zapamtiMaterijal(Materijal materijal) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.aleksandra_NJProjekatFED_war_1.0-SNAPSHOTPU");
        em = emf.createEntityManager();
        Materijal materijalProvera = em.find(Materijal.class, materijal.getSifraMaterijala());
        if (materijalProvera == null) {
            throw new Exception("Ne postoji trazen materijal za izmenu.");
        }
        em.getTransaction().begin();
        em.merge(materijal);
        em.getTransaction().commit();
        em.close();
        emf.close();
    }

}

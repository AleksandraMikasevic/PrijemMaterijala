/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author User
 */
@Entity
@Table(name = "stavkaprijemnice")
@NamedQueries({
    @NamedQuery(name = "Stavkaprijemnice.findAll", query = "SELECT s FROM Stavkaprijemnice s")
    , @NamedQuery(name = "Stavkaprijemnice.findByBrojPrijemnice", query = "SELECT s FROM Stavkaprijemnice s WHERE s.stavkaprijemnicePK.brojPrijemnice = :brojPrijemnice")
    , @NamedQuery(name = "Stavkaprijemnice.findByRedniBroj", query = "SELECT s FROM Stavkaprijemnice s WHERE s.stavkaprijemnicePK.redniBroj = :redniBroj")
    , @NamedQuery(name = "Stavkaprijemnice.findByKolicina", query = "SELECT s FROM Stavkaprijemnice s WHERE s.kolicina = :kolicina")})
public class Stavkaprijemnice implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected StavkaprijemnicePK stavkaprijemnicePK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "kolicina")
    private Double kolicina;
    @JoinColumn(name = "sifraMaterijala", referencedColumnName = "sifraMaterijala")
    @ManyToOne
    private Materijal sifraMaterijala;
    @JoinColumn(name = "brojPrijemnice", referencedColumnName = "brojPrijemnice", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Prijemnica prijemnica;

    public Stavkaprijemnice() {
        stavkaprijemnicePK = new StavkaprijemnicePK();
        sifraMaterijala = new Materijal();
    }

    public Stavkaprijemnice(StavkaprijemnicePK stavkaprijemnicePK) {
        this.stavkaprijemnicePK = stavkaprijemnicePK;
    }

    public Stavkaprijemnice(int brojPrijemnice, int redniBroj) {
        this.stavkaprijemnicePK = new StavkaprijemnicePK(brojPrijemnice, redniBroj);
    }

    public StavkaprijemnicePK getStavkaprijemnicePK() {
        return stavkaprijemnicePK;
    }

    public void setStavkaprijemnicePK(StavkaprijemnicePK stavkaprijemnicePK) {
        this.stavkaprijemnicePK = stavkaprijemnicePK;
    }

    public Double getKolicina() {
        return kolicina;
    }

    public void setKolicina(Double kolicina) {
        this.kolicina = kolicina;
    }

    public Materijal getSifraMaterijala() {
        return sifraMaterijala;
    }

    public void setSifraMaterijala(Materijal sifraMaterijala) {
        this.sifraMaterijala = sifraMaterijala;
    }

    public Prijemnica getPrijemnica() {
        return prijemnica;
    }

    public void setPrijemnica(Prijemnica prijemnica) {
        this.prijemnica = prijemnica;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stavkaprijemnicePK != null ? stavkaprijemnicePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stavkaprijemnice)) {
            return false;
        }
        Stavkaprijemnice other = (Stavkaprijemnice) object;
        if ((this.stavkaprijemnicePK == null && other.stavkaprijemnicePK != null) || (this.stavkaprijemnicePK != null && !this.stavkaprijemnicePK.equals(other.stavkaprijemnicePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.Stavkaprijemnice[ stavkaprijemnicePK=" + stavkaprijemnicePK + " ]";
    }
}

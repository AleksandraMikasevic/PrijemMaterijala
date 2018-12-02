/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author User
 */
@Entity
@Table(name = "zaposleni_uloga")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ZaposleniUloga.findAll", query = "SELECT z FROM ZaposleniUloga z")
    , @NamedQuery(name = "ZaposleniUloga.findByJmbg", query = "SELECT z FROM ZaposleniUloga z WHERE z.jmbg = :jmbg")
    , @NamedQuery(name = "ZaposleniUloga.findByUloga", query = "SELECT z FROM ZaposleniUloga z WHERE z.uloga = :uloga")})
public class ZaposleniUloga implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "JMBG")
    private String jmbg;
    @Size(max = 30)
    @Column(name = "uloga")
    private String uloga;
    @JoinColumn(name = "JMBG", referencedColumnName = "jmbg", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Zaposleni zaposleni;

    public ZaposleniUloga() {
    }

    public ZaposleniUloga(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getUloga() {
        return uloga;
    }

    public void setUloga(String uloga) {
        this.uloga = uloga;
    }

    public Zaposleni getZaposleni() {
        return zaposleni;
    }

    public void setZaposleni(Zaposleni zaposleni) {
        this.zaposleni = zaposleni;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jmbg != null ? jmbg.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ZaposleniUloga)) {
            return false;
        }
        ZaposleniUloga other = (ZaposleniUloga) object;
        if ((this.jmbg == null && other.jmbg != null) || (this.jmbg != null && !this.jmbg.equals(other.jmbg))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.ZaposleniUloga[ jmbg=" + jmbg + " ]";
    }
    
}

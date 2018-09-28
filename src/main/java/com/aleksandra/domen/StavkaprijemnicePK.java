/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aleksandra.domen;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;

/**
 *
 * @author User
 */
@Embeddable
public class StavkaprijemnicePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "brojPrijemnice")
    private int brojPrijemnice;
    @Basic(optional = false)
    @NotNull
    @Column(name = "redniBroj")
    private int redniBroj;

    public StavkaprijemnicePK() {
    }

    public StavkaprijemnicePK(int brojPrijemnice, int redniBroj) {
        this.brojPrijemnice = brojPrijemnice;
        this.redniBroj = redniBroj;
    }

    public int getBrojPrijemnice() {
        return brojPrijemnice;
    }

    public void setBrojPrijemnice(int brojPrijemnice) {
        this.brojPrijemnice = brojPrijemnice;
    }

    public int getRedniBroj() {
        return redniBroj;
    }

    public void setRedniBroj(int redniBroj) {
        this.redniBroj = redniBroj;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) brojPrijemnice;
        hash += (int) redniBroj;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkaprijemnicePK)) {
            return false;
        }
        StavkaprijemnicePK other = (StavkaprijemnicePK) object;
        if (this.brojPrijemnice != other.brojPrijemnice) {
            return false;
        }
        if (this.redniBroj != other.redniBroj) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.StavkaprijemnicePK[ brojPrijemnice=" + brojPrijemnice + ", redniBroj=" + redniBroj + " ]";
    }
    
}

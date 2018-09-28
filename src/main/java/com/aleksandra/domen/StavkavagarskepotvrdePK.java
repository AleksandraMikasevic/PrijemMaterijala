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
public class StavkavagarskepotvrdePK implements Serializable {

    @Basic(optional = false)
    @NotNull
    @Column(name = "brojVagarskePotvrde")
    private int brojVagarskePotvrde;
    @Basic(optional = false)
    @NotNull
    @Column(name = "brojStavke")
    private int brojStavke;

    public StavkavagarskepotvrdePK() {
    }

    public StavkavagarskepotvrdePK(int brojVagarskePotvrde, int brojStavke) {
        this.brojVagarskePotvrde = brojVagarskePotvrde;
        this.brojStavke = brojStavke;
    }

    public int getBrojVagarskePotvrde() {
        return brojVagarskePotvrde;
    }

    public void setBrojVagarskePotvrde(int brojVagarskePotvrde) {
        this.brojVagarskePotvrde = brojVagarskePotvrde;
    }

    public int getBrojStavke() {
        return brojStavke;
    }

    public void setBrojStavke(int brojStavke) {
        this.brojStavke = brojStavke;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) brojVagarskePotvrde;
        hash += (int) brojStavke;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StavkavagarskepotvrdePK)) {
            return false;
        }
        StavkavagarskepotvrdePK other = (StavkavagarskepotvrdePK) object;
        if (this.brojVagarskePotvrde != other.brojVagarskePotvrde) {
            return false;
        }
        if (this.brojStavke != other.brojStavke) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.aleksandra.domen.StavkavagarskepotvrdePK[ brojVagarskePotvrde=" + brojVagarskePotvrde + ", brojStavke=" + brojStavke + " ]";
    }
    
}

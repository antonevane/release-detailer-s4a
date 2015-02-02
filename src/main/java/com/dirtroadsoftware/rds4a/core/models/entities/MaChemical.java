package com.dirtroadsoftware.rds4a.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 */
@Entity
@Table(name="ma_chemical_dev")
public class MaChemical {
    @Id
    private Long id;
    private String chemical;
    private String rtn;
    private Double amount;

    private String units;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="release_id", referencedColumnName = "id") // 'release_id' ma_chemical table refs 'id' in ma_release
    private MaRelease release;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChemical() {
        return chemical;
    }

    public MaRelease getRelease() {
        return release;
    }

    public String getRtn() {
        return rtn;
    }

    public Double getAmount() {
        return amount;
    }

    public String getUnits() {
        return units;
    }

    public void setChemical(String chemical) {
        this.chemical = chemical;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setUnits(String units) {
        this.units = units;
    }

    public void setRelease(MaRelease release) {
        this.release = release;
    }
}

package com.dirtroadsoftware.rds4a.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 */
//@Entity
//@Table(name="ma_chemical_dev")
public class MaChemical {
    private Long id;

    @Id
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

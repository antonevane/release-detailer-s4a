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
@Table(name="ma_source_dev")
public class MaSource {
    @Id
    private Long id;
    private String source;
    private String rtn;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="release_id", referencedColumnName = "id") // 'release_id' ma_source table refs 'id' in ma_release
    private MaRelease release;

    public String getSource() {
        return source;
    }

    public String getRtn() {
        return rtn;
    }

    public MaRelease getRelease() {
        return release;
    }
}

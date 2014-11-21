package com.dirtroadsoftware.rds4a.core.models.entities;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 *
 */
@Entity
@Table(name="ma_action_dev")
public class MaAction {
    @Id
    private Long id;

    private String action;
    private Date date;
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="release_id") // 'release_id' column in ma_release_dev table
    private MaRelease release;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public MaRelease getRelease() {
        return release;
    }

    public void setRelease(MaRelease release) {
        this.release = release;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

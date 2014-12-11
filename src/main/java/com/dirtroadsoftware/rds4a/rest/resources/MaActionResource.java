package com.dirtroadsoftware.rds4a.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.Date;

/**
 *
 */
public class MaActionResource extends ResourceSupport {

    private Long rid;
    private String action;
    private String date;
    private String rtn;
    private String status;

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getRid() {
        return rid;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        return action;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setRtn(String rtn) {
        this.rtn = rtn;
    }

    public String getRtn() {
        return rtn;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

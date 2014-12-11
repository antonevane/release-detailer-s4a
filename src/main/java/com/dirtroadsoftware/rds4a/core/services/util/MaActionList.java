package com.dirtroadsoftware.rds4a.core.services.util;

import com.dirtroadsoftware.rds4a.core.models.entities.MaAction;

import java.util.List;

/**
 *
 */
public class MaActionList {
    private List<MaAction> actions;

    public MaActionList(List<MaAction> actions) {
        this.actions = actions;
    }

    public List<MaAction> getActions() {
        return actions;
    }

    public void setActions(List<MaAction> actions) {
        this.actions = actions;
    }
}

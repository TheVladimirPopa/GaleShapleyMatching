package com.company.Backend;

import java.util.ArrayList;
import java.util.List;

public class Proposer {

    private final String name;
    private final List<Acceptor> preferences = new ArrayList<>();
    protected boolean free;
    private Acceptor match;
    private int current = 0;

    public Proposer(String name) {
        this.name = name;
        free = true;
    }

    public String getName() {
        return name;
    }

    public int getCurrent() {
        return current;
    }

    public void incCurrent() {
        current++;
    }

    public void propose(Acceptor p) {
        if (p.isFree()) {
            setMatch(p);
            p.setMatch(this);
            match.setFree(false);
            free = false;
        } else if (p.getRank(this) < p.getRank(p.getMatch())) {
            if (match != null) match.setFree(true);

            /**
             * This sets the previous match to a single status
             * **/
            p.getMatch().setMatch(null);
            p.getMatch().setFree(true);

            setMatch(p);
            p.setMatch(this);
            match.setFree(false);
            free = false;
        }
    }

    public boolean isFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }

    public Acceptor getMatch() {
        return match;
    }

    public void setMatch(Acceptor match) {
        this.match = match;
    }

    public List<Acceptor> getPreferences() {
        return preferences;
    }

    @Override
    public String toString() {
        return name;
    }
}

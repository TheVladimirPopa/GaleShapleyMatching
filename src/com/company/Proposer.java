package com.company;

import java.util.ArrayList;
import java.util.List;

public class Proposer {

    private final String name;
    protected boolean free;
    private Acceptor match;
    private List<Acceptor> preferences = new ArrayList<>();
    private int current = 0;

    public Proposer(String name) {
        this.name = name;
        free = true;
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
            match.free = false;
            free = false;
        } else if (p.getRank(this) < p.getRank(p.getMatch0())) {
            match.free = true;
            setMatch(p);
            p.setMatch(this);
            match.free = false;
            free = false;
        }
    }

    public boolean isFree() {
        return free;
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

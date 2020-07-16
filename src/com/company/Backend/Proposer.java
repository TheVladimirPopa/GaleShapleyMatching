package com.company.Backend;

import java.util.ArrayList;
import java.util.List;

public class Proposer {

    private final String name;
    private final List<Acceptor> preferences = new ArrayList<>();
    private Acceptor match;
    private int current = 0;

    public Proposer(String name) {
        this.name = name;
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
        if (p.isNotEngaged()) {
            setMatch(p);
            p.setMatch(this);
        } else if (p.getRank(this) < p.getRank(p.getMatch())) {
            if (match != null) {
                match.setMatch(null);
            }
            p.getMatch().incCurrent();
            p.getMatch().setMatch(null);

            setMatch(p);
            p.setMatch(this);
        } else incCurrent();
    }

    public boolean isNotEngaged() {
        return match == null;
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

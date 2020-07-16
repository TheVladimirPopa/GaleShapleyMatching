package com.company.Backend;

import java.util.ArrayList;
import java.util.List;

public class Acceptor {

    private final String name;
    private final List<Proposer> preferences = new ArrayList<>();
    private Proposer match;

    public Acceptor(String name) {
        this.name = name;
    }

    protected int getRank(Proposer proposer) {
        for (int i = 0; i < preferences.size(); i++)
            if (preferences.get(i).equals(proposer))
                return i;
        return 0;
    }

    public boolean isNotEngaged() {
        return match == null;
    }

    public String getName() {
        return name;
    }

    public List<Proposer> getPreferences0() {
        return preferences;
    }

    protected Proposer getMatch() {
        return match;
    }

    public void setMatch(Proposer match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return name;
    }
}

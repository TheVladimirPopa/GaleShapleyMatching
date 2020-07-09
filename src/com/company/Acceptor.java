package com.company;

import java.util.ArrayList;
import java.util.List;

public class Acceptor extends Proposer {

    private Proposer match;
    private List<Proposer> preferences = new ArrayList<>();

    public Acceptor(String name) {
        super(name);
    }

    public void setMatch(Proposer match) {
        this.match = match;
    }

    protected int getRank(Proposer proposer) {
        for (int i = 0; i < preferences.size(); i++)
            if (preferences.get(i).equals(proposer))
                return i;
        return 0;
    }

    public List<Proposer> getPreferences0() {
        return preferences;
    }

    protected Proposer getMatch0() {
        return match;
    }
}

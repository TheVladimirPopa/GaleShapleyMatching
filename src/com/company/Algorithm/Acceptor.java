package com.company.Algorithm;

import java.util.HashMap;

public class Acceptor extends Actor {

    private final HashMap<Proposer, Integer> ranks;

    public Acceptor(String name) {
        super(name);
        ranks = new HashMap<>();
    }

    public HashMap<Proposer, Integer> getPreferenceRanks() {
        return ranks;
    }

    protected int getRank(Proposer proposer) {
        return ranks.get(proposer);
    }

}

package com.company.Backend;

import java.util.HashMap;

public class Acceptor extends Actor {

    private HashMap<Proposer, Integer> ranks;

    public Acceptor(String name) {
        super(name);
        ranks = new HashMap<>();
    }

    public HashMap<Proposer, Integer> getPreferenceRanks() {
        return ranks;
    }

    protected int getRank(Proposer proposer) {
//        for (int i = 0; i < getPreferences().size(); i++)
//            if (getPreferences().get(i).equals(proposer))
//                return i;
        return ranks.get(proposer);
    }

}

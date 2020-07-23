package com.company.Backend;

public class Acceptor extends Actor {

    public Acceptor(String name) {
        super(name);
    }

    protected int getRank(Proposer proposer) {
        for (int i = 0; i < getPreferences().size(); i++)
            if (getPreferences().get(i).equals(proposer))
                return i;
        return 0;
    }

}

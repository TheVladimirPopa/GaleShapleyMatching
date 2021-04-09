package com.company.Algorithm;

public class Proposer extends Actor {

    private int current = 0;

    public Proposer(String name) {
        super(name);
    }

    public int getCurrent() {
        return current;
    }

    public void incCurrent() {
        current++;
    }

    public void propose(Acceptor choice) {
        if (choice.isNotEngaged()) {
            setMatch(choice);
            choice.setMatch(this);
        } else if (choice.getRank(this) < choice.getRank((Proposer) choice.getMatch())) {

            if (getMatch() != null) getMatch().setMatch(null);

            ((Proposer) choice.getMatch()).incCurrent();
            choice.getMatch().setMatch(null);

            setMatch(choice);
            choice.setMatch(this);
        } else incCurrent();
    }

}

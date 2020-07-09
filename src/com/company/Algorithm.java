package com.company;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private static final List<Proposer> proposerList = new ArrayList<>();
    private static final List<Acceptor> acceptorList = new ArrayList<>();

    private static boolean isEngaged(List<Proposer> proposerList) {
        for (Proposer p : proposerList)
            if (p.isFree())
                return false;
        return true;
    }

    public static void calculateMatches() {
        while (!isEngaged(proposerList)) {
            for (Proposer p : proposerList) {
                if (p.isFree())
                    p.propose(p.getPreferences().get(p.getCurrent()));
                if (p.isFree()) p.incCurrent();
            }
        }
    }

    public static void printMatches() {
        for (Proposer p : proposerList)
            System.out.println(p + " " + p.getMatch());
    }

    public static void main(String[] args) {
        /** list of men **/
        String[] m = {"M1", "M2", "M3", "M4", "M5"};
        /** list of women **/
        String[] w = {"W1", "W2", "W3", "W4", "W5"};

        /** men preference **/
        String[][] mp = {{"W5", "W2", "W3", "W4", "W1"},
                {"W2", "W5", "W1", "W3", "W4"},
                {"W4", "W3", "W2", "W1", "W5"},
                {"W1", "W2", "W3", "W4", "W5"},
                {"W5", "W2", "W3", "W4", "W1"}};
        /** women preference **/
        String[][] wp = {{"M5", "M3", "M4", "M1", "M2"},
                {"M1", "M2", "M3", "M5", "M4"},
                {"M4", "M5", "M3", "M2", "M1"},
                {"M5", "M2", "M1", "M4", "M3"},
                {"M2", "M1", "M4", "M3", "M5"}};

        for (String s : m) Algorithm.proposerList.add(new Proposer(s));
        for (String s : w) Algorithm.acceptorList.add(new Acceptor(s));

        int index = 0;
        for (Proposer proposer : proposerList) {
            for (int i = 0; i < mp[index].length; i++)
                proposer.getPreferences().add(new Acceptor(mp[index][i]));
            index++;
        }

        index = 0;
        for (Acceptor acceptor : acceptorList) {
            for (int i = 0; i < mp[index].length; i++)
                acceptor.getPreferences0().add(new Proposer(wp[index][i]));
            index++;
        }


        Algorithm.calculateMatches();
        Algorithm.printMatches();
    }
}

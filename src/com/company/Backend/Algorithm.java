package com.company.Backend;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private static final List<Proposer> proposerList = new ArrayList<>();
    private static final List<Acceptor> acceptorList = new ArrayList<>();

    private static boolean isEngaged() {
        for (Proposer proposer : Algorithm.proposerList)
            if (proposer.isFree())
                return false;
        return true;
    }

    public static void calculateMatches() {
        int day = 1;
        while (!isEngaged()) {
            for (Proposer proposer : proposerList) {
                if (proposer.isFree())
                    proposer.propose(proposer.getPreferences().get(proposer.getCurrent()));
                if (proposer.isFree()) proposer.incCurrent();
            }
            printMatches(day);
            day++;
        }
    }

    public static void printMatches(int day) {
        System.out.print("D" + day+ ": ");
        for (Proposer p : proposerList) {
            System.out.print(p + " - " + p.getMatch());
            if(!p.equals(proposerList.get(proposerList.size()-1)))
                System.out.print(", ");
            else
                System.out.print('\n');
        }
    }


    // Test Main
    public static void main(String[] args) {
        // list of men
        String[] m = {"M1", "M2", "M3", "M4", "M5"};
        // list of women
        String[] w = {"W1", "W2", "W3", "W4", "W5"};

        // men preference
        String[][] mp = {{"W5", "W2", "W3", "W4", "W1"},
                {"W2", "W5", "W1", "W3", "W4"},
                {"W4", "W3", "W2", "W1", "W5"},
                {"W1", "W2", "W3", "W4", "W5"},
                {"W5", "W2", "W3", "W4", "W1"}};
        // women preference
        String[][] wp = {{"M5", "M3", "M4", "M1", "M2"},
                {"M1", "M2", "M3", "M5", "M4"},
                {"M4", "M5", "M3", "M2", "M1"},
                {"M5", "M2", "M1", "M4", "M3"},
                {"M2", "M1", "M4", "M3", "M5"}};

        char prop = 'm'; //Changes the propose (m/w)

        if (prop == 'm') input(m, w, mp, wp); // When men propose
        else input(w, m, wp, mp); // When women proposer

        Algorithm.calculateMatches();
    }


    /**
     * Input methods
     **/

    public static void input(String[] m, String[] w, String[][] mp, String[][] wp) {
        for (String s : m) Algorithm.proposerList.add(new Proposer(s));
        for (String s : w) Algorithm.acceptorList.add(new Acceptor(s));

        int index = 0;
        for (Proposer proposer : proposerList) {
            for (int i = 0; i < mp[index].length; i++)
                proposer.getPreferences().add(matchStringToObject0(mp[index][i]));
            index++;
        }

        index = 0;
        for (Acceptor acceptor : acceptorList) {
            for (int i = 0; i < wp[index].length; i++)
                acceptor.getPreferences0().add(matchStringToObject(wp[index][i]));
            index++;
        }
    }

    private static Proposer matchStringToObject(String s) {
        for (Proposer p : proposerList) {
            if (s.equals(p.getName())) {
                return p;
            }
        }
        return null;
    }

    private static Acceptor matchStringToObject0(String s) {
        for (Acceptor a : acceptorList) {
            if (s.equals(a.getName())) {
                return a;
            }
        }
        return null;
    }
}

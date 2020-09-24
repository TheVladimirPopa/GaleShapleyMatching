package com.company.Backend;

import java.util.ArrayList;
import java.util.List;

public class Algorithm {

    private static final List<Proposer> proposerList = new ArrayList<>();
    private static final List<Acceptor> acceptorList = new ArrayList<>();

    private static boolean isEveryOneEngaged() {
        for (Proposer proposer : Algorithm.proposerList)
            if (proposer.isNotEngaged())
                return false;
        return true;
       // return engagedCount == proposerList.size();
    }

    public static void calculateMatches() {
        int day = 1;
        while (!isEveryOneEngaged()) {
            for (Proposer proposer : proposerList)
                if (proposer.isNotEngaged()) {
                    proposer.propose((Acceptor) proposer.getPreferences().get(proposer.getCurrent()));
                }
            printMatches(day);
            day++;
        }
    }

    private static void printMatches(int day) {
        System.out.print("Day " + day + ": " + '\n');
        for (Proposer p : proposerList) {
            System.out.print(p + " - " + p.getMatch());
            if (p.isNotEngaged())
                System.out.print(" (proposed to or was previously matched with " + p.getPreferences().get(p.getCurrent() - 1) + ")");
            if (!p.equals(proposerList.get(proposerList.size() - 1))) System.out.print("\n");
            else System.out.println("\n");
        }
    }


    // Test Main
    public static void main(String[] args) {
        // list of men
        String[] m = {"m1", "m2", "m3", "m4"};
        // list of women
        String[] w = {"f1", "f2", "f3", "f4"};

        // men preference
        String[][] mp = {{"f3", "f2", "f1", "f4"},
                {"f4", "f1", "f3", "f2"},
                {"f4", "f2", "f3", "f1"},
                {"f2", "f3", "f4", "f1"}};
        // women preference
        String[][] wp = {{"m1", "m3", "m4", "m2"},
                {"m1", "m4", "m3", "m2"},
                {"m4", "m2", "m3", "m1"},
                {"m2", "m4", "m1", "m3"}};

//        char prop = 'm'; //Changes the propose (m/w)
//
//        if (prop == 'm') input(m, w, mp, wp); // When men propose
//        else input(w, m, wp, mp); // When women proposer

        input(m, w, mp, wp); //the lecture says men propose

        Algorithm.calculateMatches();
    }


    /**
     * Input methods
     **/

    public static void input(String[] m, String[] w, String[][] mp, String[][] wp) {
        for (String s : m) proposerList.add(new Proposer(s));
        for (String s : w) acceptorList.add(new Acceptor(s));

        int index = 0;
        for (Proposer proposer : proposerList) {
            for (int i = 0; i < mp[index].length; i++) {
                acceptorNameCheck(mp[index][i]);
                proposer.getPreferences().add(matchStringToAcceptor(mp[index][i]));
            }
            index++;
        }

        index = 0;
        for (Acceptor acceptor : acceptorList) {
            for (int i = 0; i < wp[index].length; i++) {
                proposerNameCheck(wp[index][i]);
                acceptor.getPreferences().add(matchStringToProposer(wp[index][i]));
                acceptor.getPreferenceRanks().put((Proposer) acceptor.getPreferences().get(i), i);
            }
            index++;
        }
    }

    private static Proposer matchStringToProposer(String s) {
        for (Proposer p : proposerList) if (s.equals(p.toString())) return p;
        return null;
    }

    private static Acceptor matchStringToAcceptor(String s) {
        for (Acceptor a : acceptorList) if (s.equals(a.toString())) return a;
        return null;
    }

    private static void acceptorNameCheck(String s) {
        if (!acceptorList.contains(matchStringToAcceptor(s)))
            throw new IllegalArgumentException("Preference not available");
    }

    private static void proposerNameCheck(String s) {
        if (!proposerList.contains(matchStringToProposer(s)))
            throw new IllegalArgumentException("Preference not available");
    }
}

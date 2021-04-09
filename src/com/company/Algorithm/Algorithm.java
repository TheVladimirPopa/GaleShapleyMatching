package com.company.Algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Algorithm {

    private static final List<Proposer> proposerList = new ArrayList<>();
    private static final List<Acceptor> acceptorList = new ArrayList<>();
    static boolean testingMode = false;
    static char prop = 'm'; // Changes the propose (m/w)

    private static boolean isEveryOneEngaged() {
        for (Proposer proposer : Algorithm.proposerList)
            if (proposer.isNotEngaged())
                return false;
        return true;
    }

    public static void calculateMatches() {
        int round = 1;
        while (!isEveryOneEngaged()) {
            for (Proposer proposer : proposerList)
                if (proposer.isNotEngaged())
                    proposer.propose((Acceptor) proposer.getPreferences().get(proposer.getCurrent()));
            printMatches(round);
            round++;
        }
    }

    private static void printMatches(int round) {
        System.out.print("Round " + round + ": " + '\n');
        for (Proposer p : proposerList) {
            System.out.print(p + " - " + p.getMatch());
            if (p.isNotEngaged())
                System.out.print(" (proposed to or was previously matched with " + p.getPreferences().get(p.getCurrent() - 1) + ")");
            if (!p.equals(proposerList.get(proposerList.size() - 1))) System.out.print("\n");
            else System.out.println("\n");
        }
    }

    public static void main(String[] args) {
        // list of men
        String[] m;
        // list of women
        String[] w;

        // men preference
        String[][] mp;
        // women preference
        String[][] wp;

        if (testingMode) {
            // list of men
            m = new String[]{"m1", "m2", "m3", "m4"};
            // list of women
            w = new String[]{"f1", "f2", "f3", "f4"};

            // men preference
            mp = new String[][]{{"f3", "f2", "f1", "f4"},
                    {"f4", "f1", "f3", "f2"},
                    {"f4", "f2", "f3", "f1"},
                    {"f2", "f3", "f4", "f1"}};
            // women preference
            wp = new String[][]{{"m1", "m3", "m4", "m2"},
                    {"m1", "m4", "m3", "m2"},
                    {"m4", "m2", "m3", "m1"},
                    {"m2", "m4", "m1", "m3"}};

        } else {
            Scanner in = new Scanner(System.in);
            String aux;
            System.out.println("Input list of men (Separated by spaces)");

            aux = in.nextLine();
            m = aux.split(" ");

            System.out.println("Input list of women (Separated by spaces):");
            aux = in.nextLine();
            w = aux.split(" ");

            if (m.length != w.length)
                throw new IllegalArgumentException("Number of men != number of women");

            mp = new String[m.length][m.length];
            System.out.println("Input the men's preference orders");
            System.out.println("(Separated by spaces for each preference and by lines for each individual):");
            for (int i = 0; i < m.length; i++) {
                System.out.print(m[i] + ": ");
                aux = in.nextLine();
                mp[i] = aux.split(" ");
            }

            wp = new String[w.length][w.length];
            System.out.println("Input the women's preference orders:");
            System.out.println("(Separated by spaces for each preference and by lines for each individual):");
            for (int i = 0; i < w.length; i++) {
                System.out.print(w[i] + ": ");
                aux = in.nextLine();
                wp[i] = aux.split(" ");
            }

        }

        if (prop == 'm') input(m, w, mp, wp); // When men propose
        else input(w, m, wp, mp); // When women proposer

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
            throw new IllegalArgumentException(s + " isn't in the acceptor list");
    }

    private static void proposerNameCheck(String s) {
        if (!proposerList.contains(matchStringToProposer(s)))
            throw new IllegalArgumentException(s + " isn't in the proposer list");
    }
}

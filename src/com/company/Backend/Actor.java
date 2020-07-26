package com.company.Backend;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor {
    private final String name;
    private final List<Actor> preferences;
    private Actor match;

    protected Actor(String name) {
        this.name = name;
        preferences = new ArrayList<>();
    }

    public Actor getMatch() {
        return match;
    }

    public void setMatch(Actor match) {
        this.match = match;
    }

    public List<Actor> getPreferences() {
        return preferences;
    }

    public boolean isNotEngaged() {
        return match == null;
    }
    
    @Override
    public String toString() {
        return name;
    }

}

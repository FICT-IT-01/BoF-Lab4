package com.kpi.it01.team1.models;

import java.util.ArrayList;

public class Institute {
    private String name;
    private final ArrayList<Faculty> faculties;

    public Institute(String name) {
        this.name = name;
        faculties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }
}
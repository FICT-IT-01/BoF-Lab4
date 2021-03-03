package com.kpi.it01.team1.models;

import java.util.HashMap;

public class Institute {
    private String name;
    private final HashMap<String, Faculty> faculties;

    public Institute(String name) {
        this.name = name;
        faculties = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public HashMap<String, Faculty> getFaculties() {
        return faculties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Institute institute = (Institute) o;

        if (!name.equals(institute.name)) return false;
        return faculties.equals(institute.faculties);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + faculties.hashCode();
        return result;
    }
}
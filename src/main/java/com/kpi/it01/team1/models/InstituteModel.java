package com.kpi.it01.team1.models;

import java.util.ArrayList;

public class InstituteModel {
    private final String name;
    private final ArrayList<FacultyModel> faculties;

    public InstituteModel(String name) {
        this.name = name;
        faculties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<FacultyModel> getFaculties() {
        return faculties;
    }
}
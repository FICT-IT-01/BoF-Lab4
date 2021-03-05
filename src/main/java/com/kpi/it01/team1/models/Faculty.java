package com.kpi.it01.team1.models;

import java.util.ArrayList;

public class Faculty {
    private String name;
    private final ArrayList<Student> students;

    public Faculty(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Student> getStudents() {
        return students;
    }
}
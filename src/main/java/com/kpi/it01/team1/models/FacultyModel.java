package com.kpi.it01.team1.models;

import java.util.ArrayList;

public class FacultyModel {
    private final String name;
    private final ArrayList<StudentModel> students;


    public FacultyModel(String name) {
        this.name = name;
        students = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<StudentModel> getStudents() {
        return students;
    }
}

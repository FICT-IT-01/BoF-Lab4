package com.kpi.it01.team1.models;

import java.util.ArrayList;

public class InstituteModel {
    private final String name;
    private final ArrayList<FacultyModel> faculties;
    private int totalAmountOfStudents;
    private FacultyModel biggestFaculty;
    private ArrayList<StudentModel> studentsWithMarkInRange;

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

    public ArrayList<StudentModel> getStudentsWithMarkInRange() {
        return studentsWithMarkInRange;
    }

    public void setTotalAmountOfStudents(int totalAmountOfStudents) {
        this.totalAmountOfStudents = totalAmountOfStudents;
    }

    public void setStudentsWithMarkInRange(ArrayList<StudentModel> studentsWithMarkInRange) {
        this.studentsWithMarkInRange = studentsWithMarkInRange;
    }

    public int getTotalAmountOfStudents() {
        return totalAmountOfStudents;
    }

    public FacultyModel getBiggestFaculty() {
        return biggestFaculty;
    }

    public void setBiggestFaculty(FacultyModel biggestFaculty) {
        this.biggestFaculty = biggestFaculty;
    }
}
package com.kpi.it01.team1.models;

import java.util.HashSet;

public class Faculty {
    private String name;
    private final HashSet<Student> students;

    public Faculty(String name) {
        this.name = name;
        students = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public HashSet<Student> getStudents() {
        return students;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Faculty faculty = (Faculty) o;

        if (!name.equals(faculty.name)) return false;
        return students.equals(faculty.students);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + students.hashCode();
        return result;
    }
}
package com.kpi.it01.team1.data.providers;

import com.kpi.it01.team1.models.FacultyModel;
import com.kpi.it01.team1.models.InstituteModel;

import java.util.ArrayList;

public class InMemoryDataProvider {
    private static final ArrayList<InstituteModel> institutes = new ArrayList<>();

    public static ArrayList<InstituteModel> getInstitutes() {
        return institutes;
    }

    public static void addInstitute(InstituteModel institute) {
        institutes.add(institute);
    }

    public static InstituteModel getInstituteByName(String name) {
        return institutes.stream().filter(inst -> inst.getName().equals(name)).findFirst().orElse(null);
    }

    public static FacultyModel getFacultyByName(String instituteName, String facultyName) {
        return getInstituteByName(instituteName)
                .getFaculties().stream().filter(f -> f.getName().equals(facultyName)).findFirst().orElse(null);
    }
}

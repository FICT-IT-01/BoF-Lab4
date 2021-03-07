package com.kpi.it01.team1.services;

import com.kpi.it01.team1.models.FacultyModel;
import com.kpi.it01.team1.models.InstituteModel;
import com.kpi.it01.team1.models.MarkRange;
import com.kpi.it01.team1.models.StudentModel;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class InstituteInformationFounderService {
    public int getTotalStudentsAmount(InstituteModel institute) {
        return institute.getFaculties().stream().mapToInt(f -> f.getStudents().size()).sum();
    }

    public FacultyModel getBiggestFaculty(InstituteModel institute) {
        return institute.getFaculties().stream().max((o1, o2) -> {
            if (o1.getStudents().size() > o2.getStudents().size())
                return 1;
            else if (o1.getStudents().size() < o2.getStudents().size())
                return -1;
            return 0;
        }).orElse(null);
    }

    public ArrayList<StudentModel> getStudentsWithAverageMarkInRange(InstituteModel institute, MarkRange range) {
        ArrayList<StudentModel> students = new ArrayList<>();

        institute.getFaculties().forEach(f -> students.addAll(f.getStudents().stream()
                .filter(s -> s.getAverageMark() >= range.getMinMark()
                        && s.getAverageMark() <= range.getMaxMark())
                .collect(Collectors.toCollection(ArrayList::new))));

        return students;
    }
}

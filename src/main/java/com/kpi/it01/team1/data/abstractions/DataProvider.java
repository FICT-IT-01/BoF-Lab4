package com.kpi.it01.team1.data.abstractions;

import com.kpi.it01.team1.models.FacultyModel;
import com.kpi.it01.team1.models.InstituteModel;

public interface DataProvider {
    InstituteModel getInstituteByName(String name);
    FacultyModel getFacultyByName(String name);
}

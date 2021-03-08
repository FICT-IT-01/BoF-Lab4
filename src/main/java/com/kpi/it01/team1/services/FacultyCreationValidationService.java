package com.kpi.it01.team1.services;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.FacultyModel;
import com.kpi.it01.team1.models.RequestValidationModel;
import com.kpi.it01.team1.models.ValidationResultModel;
import com.kpi.it01.team1.utils.StringUtils;

public class FacultyCreationValidationService implements ValidationService<FacultyModel> {
    private RequestValidationModel<FacultyModel> requestValidationModel;
    private FacultyModel facultyModel;
    private ValidationResultModel validationResult;

    @Override
    public void setRequestValidationModel(RequestValidationModel<FacultyModel> requestValidationModel) {
        validationResult = new ValidationResultModel();
        this.requestValidationModel = requestValidationModel;
    }

    @Override
    public ValidationResultModel validate() {
        validationResult = new ValidationResultModel();

        facultyModel = requestValidationModel.getModel();

        checkFacultyName();
        checkIfFacultyAlreadyExists();

        return validationResult;
    }

    private void checkFacultyName() {
        if (StringUtils.isNullOrEmpty(facultyModel.getName())
                || StringUtils.isNullOrWhitespace(facultyModel.getName())) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Faculty name must be not null, empty or whitespace");
        }
    }

    private void checkIfFacultyAlreadyExists() {
        String instituteName = requestValidationModel.getRequest().getParameter(Constants.INSTITUTE_PARAMETER_NAME);
        String facultyName = requestValidationModel.getRequest().getParameter(Constants.FACULTY_PARAMETER_NAME);

        boolean isFacultyExists = InMemoryDataProvider.getInstituteByName(instituteName)
                .getFaculties().stream().anyMatch(f -> f.getName().equals(facultyName));

        if (isFacultyExists) {
            validationResult.setValidationResult(false);
            validationResult
                .addValidationError("Faculty with name " + facultyName + " at " + instituteName + " is already exists");
        }
    }
}

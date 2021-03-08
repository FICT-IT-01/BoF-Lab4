package com.kpi.it01.team1.services;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.RequestValidationModel;
import com.kpi.it01.team1.models.StudentModel;
import com.kpi.it01.team1.models.ValidationResultModel;
import com.kpi.it01.team1.utils.StringUtils;

public class StudentCreationValidationService<T extends StudentModel> implements ValidationService {
    private RequestValidationModel<StudentModel> requestValidationModel;
    private StudentModel student;
    private ValidationResultModel validationResult;

    @Override
    public void setRequestValidationModel(RequestValidationModel request) {
        validationResult = new ValidationResultModel();
        this.requestValidationModel = request;
    }

    @Override
    public ValidationResultModel validate() {
        validationResult = new ValidationResultModel();

        student = requestValidationModel.getModel();

        isStudentFirstNameValid();
        isStudentLastNameValid();
        isStudentBookNumberValid();
        checkIfStudentWithSameBookIdNotExists();
        isAverageMarkValid();

        return validationResult;
    }

    private void isStudentFirstNameValid() {
        if (StringUtils.isNullOrWhitespace(student.getFirstName()) ||
                StringUtils.isNullOrEmpty(student.getFirstName())) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Student first name can`t be null empty or whitespace");
        }
    }

    private void isStudentLastNameValid() {
        if (StringUtils.isNullOrWhitespace(student.getLastName()) ||
                StringUtils.isNullOrEmpty(student.getLastName())) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Student last name can`t be null empty or whitespace");
        }
    }

    private void isStudentBookNumberValid() {
        if (StringUtils.isNullOrWhitespace(student.getBookNumber()) ||
                StringUtils.isNullOrEmpty(student.getBookNumber())) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Student book number can`t be null empty or whitespace");
        }
    }


    private void checkIfStudentWithSameBookIdNotExists() {
        String instituteName = requestValidationModel.getRequest().getParameter(Constants.INSTITUTE_PARAMETER_NAME);
        String facultyName = requestValidationModel.getRequest().getParameter(Constants.FACULTY_PARAMETER_NAME);

        boolean isStudentWithSameBookNumberExists = InMemoryDataProvider.getFacultyByName(instituteName, facultyName)
                .getStudents().stream().anyMatch(s -> s.getBookNumber().equals(student.getBookNumber()));

        if (isStudentWithSameBookNumberExists) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Student with same book number is already exists");
        }
    }

    private void isAverageMarkValid() {
        boolean isAverageMarkValid = (student.getAverageMark() < (float) 0) && (student.getAverageMark() > (float) 100);

        if (isAverageMarkValid) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Average mark must be in range [0; 100]");
        }
    }
}

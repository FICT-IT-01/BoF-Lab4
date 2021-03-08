package com.kpi.it01.team1.models;

import java.util.ArrayList;

public class ValidationResultModel {
    private boolean validationResult;
    private ArrayList<String> validationErrors;

    public ValidationResultModel() {
        validationResult = true;
        this.validationErrors = new ArrayList<>();
    }

    public void setValidationResult(boolean validationResult) {
        this.validationResult = validationResult;
    }

    public boolean isValid() {
        return validationResult;
    }

    public ArrayList<String> getErrors() {
        return validationErrors;
    }

    public void addValidationError(String validationError) {
        this.validationErrors.add(validationError);
    }
}

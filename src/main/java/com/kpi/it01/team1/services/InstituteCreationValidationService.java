package com.kpi.it01.team1.services;

import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.InstituteModel;
import com.kpi.it01.team1.models.RequestValidationModel;
import com.kpi.it01.team1.models.ValidationResultModel;
import com.kpi.it01.team1.utils.StringUtils;

public class InstituteCreationValidationService implements ValidationService<InstituteModel> {
    private RequestValidationModel<InstituteModel> requestValidationModel;
    private InstituteModel institute;
    private ValidationResultModel validationResult;


    @Override
    public void setRequestValidationModel(RequestValidationModel<InstituteModel> requestValidationModel) {
        validationResult = new ValidationResultModel();
        this.requestValidationModel = requestValidationModel;
    }

    @Override
    public ValidationResultModel validate() {
        validationResult = new ValidationResultModel();

        institute = requestValidationModel.getModel();

        checkInstituteName();
        checkIfInstituteAlreadyExists();

        return validationResult;
    }

    private void checkInstituteName() {
        if (StringUtils.isNullOrEmpty(institute.getName())
                || StringUtils.isNullOrWhitespace(institute.getName())) {
            validationResult.setValidationResult(false);
            validationResult.addValidationError("Faculty name must be not null, empty or whitespace");
        }
    }

    private void checkIfInstituteAlreadyExists() {
        boolean isInstituteExists = InMemoryDataProvider
                .getInstitutes().stream().anyMatch(inst -> inst.getName().equals(institute.getName()));

        if (isInstituteExists) {
            validationResult.setValidationResult(false);
            validationResult
                    .addValidationError("Institute with name " + institute.getName() + " is already exists");
        }
    }

}

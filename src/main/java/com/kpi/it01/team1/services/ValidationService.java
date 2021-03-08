package com.kpi.it01.team1.services;

import com.kpi.it01.team1.models.RequestValidationModel;
import com.kpi.it01.team1.models.ValidationResultModel;

public interface ValidationService<T> {
    void setRequestValidationModel(RequestValidationModel<T> requestValidationModel);
    ValidationResultModel validate();
}

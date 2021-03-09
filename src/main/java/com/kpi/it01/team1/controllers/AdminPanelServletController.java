package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.FacultyModel;
import com.kpi.it01.team1.models.InstituteModel;
import com.kpi.it01.team1.models.RequestValidationModel;
import com.kpi.it01.team1.models.ValidationResultModel;
import com.kpi.it01.team1.services.InstituteCreationValidationService;
import com.kpi.it01.team1.services.ValidationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AdminPanelServletController", value = "/main")
public class AdminPanelServletController extends HttpServlet {
    private ValidationService validationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        validationService = new InstituteCreationValidationService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        InstituteModel institute = new InstituteModel(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME));

        validationService.setRequestValidationModel(new RequestValidationModel<>(institute, request));

        ValidationResultModel validationResult = validationService.validate();

        if (validationResult.isValid()) {
            InMemoryDataProvider.addInstitute(institute);
        }
        else {
            request.setAttribute("validationResult", validationResult);
        }

        processRequest(request, response);

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("institutes", InMemoryDataProvider.getInstitutes());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/adminPanel.jsp");
        dispatcher.forward(request, response);
    }
}

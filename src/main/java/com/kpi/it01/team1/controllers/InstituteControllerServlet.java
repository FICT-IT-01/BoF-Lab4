package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.*;
import com.kpi.it01.team1.services.FacultyCreationValidationService;
import com.kpi.it01.team1.services.InstituteInformationFounderService;
import com.kpi.it01.team1.services.ValidationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstituteControllerServlet", value = "/kpi")
public class InstituteControllerServlet extends HttpServlet {
    private ValidationService<FacultyModel> validationService;
    private InstituteInformationFounderService informationFounderService;

    @Override
    public void init() throws ServletException {
        super.init();

        validationService = new FacultyCreationValidationService();
        informationFounderService = new InstituteInformationFounderService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("goToFaculty".equals(request.getParameter("action"))) {
            try {
                facultyRequest(request, response);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getLocalizedMessage());
            }
        } else {
            processRequest(request, response, getInstituteByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME)));
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        InstituteModel institute = getInstituteByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME));

        if (institute == null) {
            response.sendError(422);
            return;
        }

        FacultyModel facultyModel = new FacultyModel(request.getParameter(Constants.FACULTY_PARAMETER_NAME));

        validationService.setRequestValidationModel(new RequestValidationModel<>(facultyModel, request));

        ValidationResultModel validationResult = validationService.validate();

        if (validationResult.isValid()) {
            if ("submit".equals(request.getParameter("action"))) {
                institute.getFaculties().add(facultyModel);
            }

            processRequest(request, response, institute);
        }
        else {
            request.setAttribute("validationResult", validationResult);

            RequestDispatcher dispatcher = request.getRequestDispatcher("/ErrorPage400.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, InstituteModel institute)
            throws ServletException, IOException {

        institute.setTotalAmountOfStudents(informationFounderService.getTotalStudentsAmount(institute));
        institute.setBiggestFaculty(informationFounderService.getBiggestFaculty(institute));
        institute.setStudentsWithMarkInRange(
                informationFounderService.getStudentsWithAverageMarkInRange(institute, new MarkRange(95, 100)));

        request.setAttribute("institute", institute);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/institute.jsp");
        dispatcher.forward(request, response);
    }

    private void facultyRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IllegalArgumentException, IOException {
        InstituteModel institute = getInstituteByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME));

        String facultyName = request.getParameter("facultyName");
        if (institute != null) {
            FacultyModel faculty = institute.getFaculties()
                    .stream().filter(
                            c -> c.getName().equals(facultyName)).findFirst()
                    .orElseThrow(
                            () -> new IllegalArgumentException("FacultyModel " + facultyName + " not found")
                    );

            request.setAttribute("faculty", faculty);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/faculty");
            dispatcher.forward(request, response);
        }
    }

    private InstituteModel getInstituteByName(String name) {
        return InMemoryDataProvider.getInstituteByName(name);
    }
}

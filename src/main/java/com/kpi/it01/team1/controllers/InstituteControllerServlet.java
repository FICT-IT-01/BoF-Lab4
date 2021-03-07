package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.abstractions.DataProvider;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.*;
import com.kpi.it01.team1.services.InstituteInformationFounderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstituteControllerServlet", value = "/kpi")
public class InstituteControllerServlet extends HttpServlet {

    //private InstituteModel kpiInstitute;
    private InstituteInformationFounderService informationFounderService;

    @Override
    public void init() throws ServletException {
        super.init();

        informationFounderService = new InstituteInformationFounderService();
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
        String facultyName = request.getParameter("facultyName");

        InstituteModel institute = getInstituteByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME));

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

        if ("submit".equals(request.getParameter("action"))) {
            institute.getFaculties().add(
                new FacultyModel(request.getParameter(Constants.FACULTY_PARAMETER_NAME)));
            processRequest(request, response, institute);
        }
    }

    private InstituteModel getInstituteByName(String name) {
        return InMemoryDataProvider.getInstituteByName(name);
    }
}

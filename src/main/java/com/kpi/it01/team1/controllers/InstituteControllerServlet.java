package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.models.*;
import com.kpi.it01.team1.services.InstituteInformationFounderService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstituteControllerServlet", value = "/kpi")
public class InstituteControllerServlet extends HttpServlet {
    private InstituteModel kpiInstitute;
    private InstituteInformationFounderService informationFounderService;

    @Override
    public void init() throws ServletException {
        super.init();

        informationFounderService = new InstituteInformationFounderService();
        kpiInstitute = new InstituteModel("KPI");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        kpiInstitute.setTotalAmountOfStudents(informationFounderService.getTotalStudentsAmount(kpiInstitute));
        kpiInstitute.setBiggestFaculty(informationFounderService.getBiggestFaculty(kpiInstitute));
        kpiInstitute.setStudentsWithMarkInRange(
                informationFounderService.getStudentsWithAverageMarkInRange(kpiInstitute, new MarkRange(95, 100)));

        request.setAttribute("institute", kpiInstitute);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/institute.jsp");
        dispatcher.forward(request, response);
    }

    private void facultyRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IllegalArgumentException, IOException {
        String facultyName = request.getParameter("facultyName");

        FacultyModel faculty = kpiInstitute.getFaculties()
                .stream().filter(
                        c -> c.getName().equals(facultyName)).findFirst()
                .orElseThrow(
                        () -> new IllegalArgumentException("FacultyModel " + facultyName + " not found")
                );

        request.setAttribute("faculty", faculty);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/faculty");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("goToFacutly".equals(request.getParameter("action"))) {
            try {
                facultyRequest(request, response);
            } catch (IllegalArgumentException e){
                System.out.println(e.getLocalizedMessage());
            }
        } else {
            processRequest(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("submit".equals(request.getParameter("action"))) {
                kpiInstitute.getFaculties().add(
                    new FacultyModel(request.getParameter("name")));
            processRequest(request, response);
        }
    }
}

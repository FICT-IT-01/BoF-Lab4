package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.models.Faculty;
import com.kpi.it01.team1.models.Institute;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "InstituteControllerServlet", value = "/kpi")
public class InstituteControllerServlet extends HttpServlet {

    Institute kpiInstitute;

    @Override
    public void init() throws ServletException {
        super.init();

        kpiInstitute = new Institute("KPI");
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("faculties", kpiInstitute.getFaculties().toArray());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/institute.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        kpiInstitute.getFaculties().add(new Faculty("FICT"));
        processRequest(request, response);
    }
}

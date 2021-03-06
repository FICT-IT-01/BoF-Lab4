package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FacultyControllerServlet", value = "/faculty")
public class FacultyControllerServlet extends HttpServlet {

    private FacultyModel faculty;

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("faculty", faculty);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/faculty.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FacultyModel newFaculty = (FacultyModel) request.getAttribute("faculty");
        if (newFaculty != null) {
            faculty = newFaculty;
        }

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if ("submit".equals(request.getParameter("action"))) {
            faculty.getStudents().add(
                    new StudentModel(
                        request.getParameter("firstName"),
                        request.getParameter("lastName"),
                        request.getParameter("bookNumber"),
                        request.getParameter("phoneNumber"),
                        Float.parseFloat(request.getParameter("averageMark")))
            );
            processRequest(request, response);
        }
    }
}

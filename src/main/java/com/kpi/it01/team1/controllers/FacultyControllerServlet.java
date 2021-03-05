package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.models.Faculty;
import com.kpi.it01.team1.models.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "FacultyControllerServlet", value = "/faculty")
public class FacultyControllerServlet extends HttpServlet {

    Faculty faculty;

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("faculty", faculty);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/faculty.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Faculty newFaculty = (Faculty) request.getAttribute("faculty");
        if (newFaculty != null) {
            faculty = newFaculty;
        }

        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if ("submit".equals(request.getParameter("action"))) {
            faculty.getStudents().add(
                    new Student(
                            request.getParameter("name")
                    )
            );
            processRequest(request, response);
        }
    }
}

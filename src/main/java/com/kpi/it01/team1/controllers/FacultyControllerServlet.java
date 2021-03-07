package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.*;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "FacultyControllerServlet", value = "/faculty")
public class FacultyControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //FacultyModel newFaculty = (FacultyModel) request.getAttribute("faculty");
        //if (newFaculty != null) {
        //    faculty = newFaculty;
        //}

        FacultyModel facultyModel = getFacultyByName(request.getParameter(Constants.FACULTY_PARAMETER_NAME));

        if (facultyModel != null) {
            processRequest(request, response, facultyModel);
        }
        else {
            // TODO: Error faculty not exists yet or smth went wrong
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FacultyModel facultyModel = getFacultyByName(request.getParameter(Constants.FACULTY_PARAMETER_NAME));

        if (facultyModel != null) {
            if ("submit".equals(request.getParameter("action"))) {
                facultyModel.getStudents().add(
                        new StudentModel(
                                request.getParameter("firstName"),
                                request.getParameter("lastName"),
                                request.getParameter("bookNumber"),
                                request.getParameter("phoneNumber"),
                                Float.parseFloat(request.getParameter("averageMark")))
                );

                processRequest(request, response, facultyModel);
            }
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, FacultyModel faculty)
            throws ServletException, IOException {
        request.setAttribute("faculty", faculty);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/faculty.jsp");
        dispatcher.forward(request, response);
    }

    private FacultyModel getFacultyByName(String name) {
        return InMemoryDataProvider.getInstituteByName("kpi").getFaculties()
                .stream().filter(f -> Objects.equals(f.getName(), name))
                .findFirst().orElse(null);
    }
}

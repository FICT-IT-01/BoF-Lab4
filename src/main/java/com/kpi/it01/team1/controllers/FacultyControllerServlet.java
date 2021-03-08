package com.kpi.it01.team1.controllers;

import com.kpi.it01.team1.Constants;
import com.kpi.it01.team1.data.providers.InMemoryDataProvider;
import com.kpi.it01.team1.models.*;
import com.kpi.it01.team1.services.StudentValidationService;
import com.kpi.it01.team1.services.ValidationService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.Objects;

@WebServlet(name = "FacultyControllerServlet", value = "/faculty")
public class FacultyControllerServlet extends HttpServlet {
    private ValidationService<StudentModel> validationService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        validationService = new StudentValidationService<StudentModel>();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        FacultyModel facultyModel = getFacultyByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME),
                                                     request.getParameter(Constants.FACULTY_PARAMETER_NAME));

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
        FacultyModel facultyModel = getFacultyByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME),
                                                     request.getParameter(Constants.FACULTY_PARAMETER_NAME));

        if (facultyModel == null) {
            // TODO: Show exception page. Required cause smth broke at parameters and it`s impossible
            //  to receive faculty model from params
        }

        StudentModel studentModel = new StudentModel(
            request.getParameter("firstName"),
            request.getParameter("lastName"),
            request.getParameter("bookNumber"),
            request.getParameter("phoneNumber"),
            Float.parseFloat(request.getParameter("averageMark").isEmpty()
                    ? "0"
                    : request.getParameter("averageMark"))
        );

        validationService.setRequestValidationModel(
            new RequestValidationModel<>(studentModel, request));

        ValidationResultModel validationResult = validationService.validate();

        if (validationResult.isValid()) {
            if ("submit".equals(request.getParameter("action"))) {
                facultyModel.getStudents().add(studentModel);
            }

            processRequest(request, response, facultyModel);
        }
        else {
            // TODO: Add show alert or a pop-up with found errors. Errors could be found at
            //  validationResult, getErrors : ArrayList<String>

            response.sendError(406, validationResult.getErrors().toString());
        }
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response, FacultyModel faculty)
            throws ServletException, IOException {
        request.setAttribute("faculty", faculty);
        request.setAttribute("institute", InMemoryDataProvider.getInstituteByName(request.getParameter(Constants.INSTITUTE_PARAMETER_NAME)));


        RequestDispatcher dispatcher = request.getRequestDispatcher("/faculty.jsp");
        dispatcher.forward(request, response);
    }

    private FacultyModel getFacultyByName(String instituteName, String facultyName) {
        return InMemoryDataProvider.getInstituteByName(instituteName).getFaculties()
                .stream().filter(f -> Objects.equals(f.getName(), facultyName))
                .findFirst().orElse(null);
    }

    private InstituteModel getInstituteByName(String instituteName) {
        return InMemoryDataProvider.getInstituteByName(instituteName);
    }
}

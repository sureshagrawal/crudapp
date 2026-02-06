package com.nsgacademy.crudapp.web;

import com.nsgacademy.crudapp.dao.StudentDAO;
import com.nsgacademy.crudapp.dao.StudentDAOImpl;
import com.nsgacademy.crudapp.exception.DAOException;
import com.nsgacademy.crudapp.model.Pagination;
import com.nsgacademy.crudapp.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() throws ServletException {
        studentDAO = new StudentDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null) action = "list";

        try {
            switch (action) {
                case "add": showNewForm(req,resp);
                    break;
                case "edit": showEditForm(req,resp);
                    break;
                case "delete": deleteStudent(req,resp);
                    break;
                case "insert": insertStudent(req,resp);
                    break;
                case "update": updateStudent(req,resp);
                    break;
                default: listStudents(req,resp);  //case "list";                    ;
            }
        }catch(DAOException e){
            req.setAttribute("errorMessage",e.getMessage());
            req.setAttribute("errorCause",e.getCause());
            req.setAttribute("errorException",e);

            e.printStackTrace(); //developers logs

            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }

    private void showNewForm(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        req.getRequestDispatcher("student-form.jsp").forward(req,resp);
    }

    private void showEditForm(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        Student student = studentDAO.getStudentById(id);
        req.setAttribute("student",student);
        req.getRequestDispatcher("student-form.jsp").forward(req,resp);
    }


    private void listStudents(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{

        int page = 1; //default page
        int pageSize = 5; //default pagesize

        if(req.getParameter("page")!=null)
            page = Integer.parseInt(req.getParameter("page"));

        if(req.getParameter("pageSize")!=null)
            pageSize = Integer.parseInt(req.getParameter("pageSize"));

        Pagination pagination = new Pagination(page,pageSize);

        int totalRecords = studentDAO.countStudents();
        int totalPages = (int) Math.ceil((double)totalRecords / pageSize);

        if(page<1)
            page = 1;
        if(page>totalPages)
            page = totalPages;

        pagination.setPage(page);

        List<Student> studentList = studentDAO.getSelectedStudents(pagination); //use model

        req.setAttribute("students",studentList); //set data for view
        req.setAttribute("totalPages",totalPages);
        req.setAttribute("currentPage",page);
        req.setAttribute("pageSize",pageSize);
        req.setAttribute("totalRecords",totalRecords);
        req.getRequestDispatcher("student-list.jsp").forward(req,resp); //call view
    }

    private void deleteStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id")); //receive data from view
        studentDAO.delete(id); //pass data to model
        resp.sendRedirect("students?action=list&success=Deleted Successfully"); //call view with success/failure attribute
    }

    private void insertStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");
        if(!validate(req,name,email,mobile)){
            req.getRequestDispatcher("student-form.jsp").forward(req,resp);
            return;
        }
        studentDAO.insert(new Student(name.trim(),email.trim(),mobile.trim()));
        resp.sendRedirect("students?action=list&success=Inserted Successfully");
    }

    private void updateStudent(HttpServletRequest req,HttpServletResponse resp) throws ServletException, IOException{
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String mobile = req.getParameter("mobile");

        if(!validate(req,name,email,mobile)){
            req.setAttribute("student",new Student(id,name,email,mobile));
            req.getRequestDispatcher("student-form.jsp").forward(req,resp);
            return;
        }

        studentDAO.update(new Student(id,name.trim(),email.trim(),mobile.trim()));
        resp.sendRedirect("students?action=list&success=Updated Successfully");
    }

    private boolean validate(HttpServletRequest req,String name,String email,String mobile){
        boolean isValid = true;

        if(name==null || !Pattern.matches("^[A-Za-z ]{3,50}$",name.trim())){
            isValid = false;
            req.setAttribute("nameError","Name should be at least 3 to 50 characters");
        }

        if(email==null || !Pattern.matches("^[A-Za-z0-9_.-]+@(.+)$",email.trim())){
            isValid = false;
            req.setAttribute("emailError","Invalid Email ID");
        }

        if(mobile==null || !Pattern.matches("^[0-9]{10}$",mobile.trim())){
            isValid = false;
            req.setAttribute("mobileError","Mobile Number should be of 10 digits");
        }

        return isValid;
    }
}

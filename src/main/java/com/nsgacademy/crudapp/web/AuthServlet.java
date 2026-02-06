package com.nsgacademy.crudapp.web;

import com.nsgacademy.crudapp.dao.UserDAO;
import com.nsgacademy.crudapp.dao.UserDAOImpl;
import com.nsgacademy.crudapp.exception.DAOException;
import com.nsgacademy.crudapp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet({"/","/auth"})
public class AuthServlet extends HttpServlet {

    private UserDAO userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null)
            action = "login";

        try{
            switch(action){
                case "register": showRegisterForm(req,resp);
                    break;
                case "logout": logoutUser(req,resp);
                    break;
                case "doLogin": loginUser(req,resp);
                    break;
                case "doRegister": registerUser(req,resp);
                    break;
                default : showLoginForm(req,resp);
            }
        }catch(DAOException e){
            req.setAttribute("errorMessage",e.getMessage());
            req.setAttribute("errorCause",e.getCause());
            req.setAttribute("errorException",e);

            e.printStackTrace(); //developers logs

            req.getRequestDispatcher("error.jsp").forward(req,resp);
        }
    }

    private void logoutUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{

        req.getSession().invalidate();
        resp.sendRedirect("auth");
    }

    private void loginUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User user = userDAO.login(username,password);

        // validation error → same page
        if(user == null){
            req.setAttribute("error","Invalid Credential");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
            return;
        }

        req.getSession().setAttribute("user",user);
        resp.sendRedirect("students"); //open CRUD project
    }

    private void registerUser(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        String name = req.getParameter("name");
        String mobile = req.getParameter("mobile");
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        // validation error → same page
        if(userDAO.exists(username)){
            req.setAttribute("error","Username already exist"); //set error attribute
            req.getRequestDispatcher("register.jsp").forward(req,resp); //return all data to same page, NO PRG
            return;
        }

        User user = new User(name,mobile,username,password);
        userDAO.register(user); //P of PRG
        resp.sendRedirect("auth?success=Registered Successfully"); //RG of PRG
    }

    private void showLoginForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        req.getRequestDispatcher("login.jsp").forward(req,resp);
    }

    private void showRegisterForm(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException,IOException{
        req.getRequestDispatcher("register.jsp").forward(req,resp);
    }
}


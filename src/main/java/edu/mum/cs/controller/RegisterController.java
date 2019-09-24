package edu.mum.cs.controller;

import edu.mum.cs.model.Advertisement;
import edu.mum.cs.model.SystemRole;
import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("fairfieldBook");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        System.out.println("here is the username:"+name);
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setActive(true);
        user.setRole(SystemRole.USER);

        EntityManager em = FBUtility.getEntityManager(request.getServletContext());
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
        RequestDispatcher  requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

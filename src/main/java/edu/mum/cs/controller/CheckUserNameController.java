package edu.mum.cs.controller;

import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CheckUserNameController")
public class CheckUserNameController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        System.out.println("comin ...");
        EntityManager em = FBUtility.getEntityManager(request.getServletContext());
        em.getTransaction().begin();
        TypedQuery<User> tq = em.createQuery("from User where name=?0 ",User.class);
        tq.setParameter(0,name);

        List<User> list = tq.getResultList();

        if(list.size()==0){
            System.out.println("user dosen't exist ");
            response.getWriter().write("no");
        }
        else{
            System.out.println("user exists ");
            response.getWriter().write("exist");
        }
        em.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

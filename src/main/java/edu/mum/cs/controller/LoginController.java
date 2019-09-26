package edu.mum.cs.controller;

import edu.mum.cs.model.SystemRole;
import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String password = request.getParameter("password");

        EntityManager em = FBUtility.getEntityManager(request.getServletContext());
        em.getTransaction().begin();
        TypedQuery<User> tq = em.createQuery("from User where name=?1 ",User.class);
        tq.setParameter(1,name);

//        User user = tq.getSingleResult();
        List<User> list = tq.getResultList();

        if(list.size()==0){
            System.out.println("user dosen't exist ");
            request.setAttribute("usererrorinfo","user dosen't exist ");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request,response);
        }
        else if(!list.get(0).getPassword().equals(password)){
            System.out.println("password wrong :"+list.get(0).getPassword()+"!="+password);
            request.setAttribute("passerrorinfo","password is incorrect");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
            requestDispatcher.forward(request,response);
        }
        else{
            request.getSession().setAttribute("user",list.get(0));
            Cookie cookie = new Cookie("userId", String.valueOf(list.get(0).getId()));
            cookie.setMaxAge(360000000);
            response.addCookie(cookie);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.html");
            requestDispatcher.forward(request,response);
        }
        em.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

package edu.mum.cs.controller;

import com.google.gson.Gson;
import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;
import org.hibernate.Transaction;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer out =response.getWriter();
        System.out.println("----------------------------------"+this.getClass().toString());
        String name = request.getParameter("name");
        String pass = request.getParameter("pass");
        String picUrl = request.getParameter("picUrl");
        System.out.println("name:"+name+" pass:"+pass+" pic:"+picUrl);
        User user = (User) request.getSession().getAttribute("user");
        user.setPassword(pass);
        user.setProfilePhotoUrl(picUrl);

        EntityManager em = FBUtility.getEntityManager(request.getServletContext());
        em.getTransaction().begin();
        em.merge(user);
        em.getTransaction().commit();
        em.close();

        out.write("sucess");
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}

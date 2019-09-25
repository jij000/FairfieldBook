package edu.mum.cs.controller;

import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import com.google.gson.*;

@WebServlet("/ProfileController")
public class ProfileController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Writer out = response.getWriter();

        String name = request.getParameter("name");
        User user=null;
        if(name==null||name==""){
            user=(User)request.getSession().getAttribute("user");
        }
        else
        {
            System.out.println("comin ...");
            EntityManager em = FBUtility.getEntityManager(request.getServletContext());
            TypedQuery<User> tq = em.createQuery("from User where name=?0 ",User.class);
            tq.setParameter(0,name);
            List<User> list = tq.getResultList();
            if(list.size()!=0)
                user = list.get(0);
            user.setFollowerNum(user.getFollowerUserList().size());
            user.setFollowingNum(user.getFollowingUserList().size());
            em.close();
        }

        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();

        final Gson gson = builder.create();

        String jsonStr  = gson.toJson(user);
        out.write(jsonStr);
    }
}

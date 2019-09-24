package edu.mum.cs.controller;

import edu.mum.cs.model.PostUpdateInfo;
import edu.mum.cs.model.User;
import edu.mum.cs.utility.FBUtility;

import javax.persistence.EntityManager;
import javax.persistence.PostUpdate;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import com.google.gson.Gson;

@WebServlet("/NotificationController")
public class NotificationController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User)request.getSession().getAttribute("user");
        if(user==null){

        }
        else
        {
            EntityManager em = FBUtility.getEntityManager(request.getServletContext());

            TypedQuery<PostUpdateInfo> tq = em.createQuery("from PostUpdateInfo where follower=?0 ",PostUpdateInfo.class);
            tq.setParameter(0,user);
            List<PostUpdateInfo> list = tq.getResultList();
            em.close();;
            response.setCharacterEncoding("utf-8");
            response.getWriter().write(list.size()+"");
        }
    }
}

package edu.mum.cs.controller;

import edu.mum.cs.model.Advertisement;
import edu.mum.cs.utility.FBUtility;
import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import javax.jws.soap.SOAPBinding;
import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/twitter2oauth_callback")
public class OAuthCallback extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Twitter twitter = TwitterFactory.getSingleton();
        RequestToken requestToken = (RequestToken) request.getSession().getAttribute("requestToken");
        String verifier = request.getParameter("oauth_verifier");
        try {
            AccessToken accessToken = twitter.getOAuthAccessToken(requestToken, verifier);
            request.getSession().removeAttribute("requestToken");
            edu.mum.cs.model.User user = (edu.mum.cs.model.User)request.getSession().getAttribute("user");
            if (user!=null){
                EntityManager em = FBUtility.getEntityManager(request.getServletContext());
                em.getTransaction().begin();
                user.setToken(accessToken.getToken());
                user.setTokenSecret(accessToken.getTokenSecret());
                em.merge(user);
                em.getTransaction().commit();
                em.close();
            }
        } catch (TwitterException e) {
            throw new ServletException(e);
        }
        response.sendRedirect(request.getContextPath() + "/");
    }

    private void test(AccessToken accessToken) throws TwitterException {
        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthAccessToken(accessToken);
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
    }
}

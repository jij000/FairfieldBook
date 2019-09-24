package edu.mum.cs.controller;

import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/twitter2oauth")
public class TwitterOAuth extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            toOAuth(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void toOAuth(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        Twitter twitter = TwitterFactory.getSingleton();
//        System.out.println(twitter.setOAuthConsumer(););
        StringBuffer callbackURL = req.getRequestURL();
        int index = callbackURL.lastIndexOf("/");
        callbackURL.replace(index, callbackURL.length(), "").append("/twitter2oauth_callback");
        System.out.println(callbackURL.toString());

        RequestToken requestToken = twitter.getOAuthRequestToken(callbackURL.toString());
        req.getSession().setAttribute("requestToken", requestToken);
        resp.sendRedirect(requestToken.getAuthenticationURL());
    }
}

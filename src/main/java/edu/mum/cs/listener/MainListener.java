package edu.mum.cs.listener;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class MainListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        // set attribute in context
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("fairfieldBook");
        context.setAttribute("emFactory", emf);

        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthConsumer(context.getInitParameter("consumerKey"), context.getInitParameter("consumerSecret"));
    }
}

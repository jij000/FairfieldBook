package edu.mum.cs.utility;

import edu.mum.cs.model.Post;
import edu.mum.cs.model.User;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.List;

public class FBUtility {
    public static EntityManager getEntityManager(ServletContext servletContext) {
        EntityManagerFactory emf = (EntityManagerFactory) servletContext.getAttribute("emFactory");
        EntityManager em = emf.createEntityManager();
        return em;
    }
    public static String getImgPath(ServletContext servletContext) {
        return servletContext.getInitParameter("imgPath");
    }

    public static List<Post> getTwitterPosts(User user) throws TwitterException {
        List<Post> posts = new ArrayList<>();
        if (null==user)
            return posts;

        Twitter twitter = TwitterFactory.getSingleton();
        twitter.setOAuthAccessToken(new AccessToken(user.getToken(),user.getTokenSecret()));
        List<Status> statuses = twitter.getHomeTimeline();
        System.out.println("Showing home timeline.");
        for (Status status : statuses) {
            System.out.println(status.getUser().getName() + ":" +
                    status.getText());
        }
        //to do
        return posts;
    }

}

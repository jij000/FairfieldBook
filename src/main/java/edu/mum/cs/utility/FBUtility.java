package edu.mum.cs.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;

public class FBUtility {
    public static EntityManager getEntityManager(ServletContext servletContext) {
        EntityManagerFactory emf = (EntityManagerFactory) servletContext.getAttribute("emFactory");
        EntityManager em = emf.createEntityManager();
        return em;
    }

    public static String getImgPath(ServletContext servletContext) {
        return servletContext.getInitParameter("imgPath");
    }

    public static String objectToJson(Object object) {
        final GsonBuilder builder = new GsonBuilder();
        builder.excludeFieldsWithoutExposeAnnotation();
        final Gson gson = builder.create();
        return gson.toJson(object);
    }
}

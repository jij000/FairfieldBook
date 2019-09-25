package edu.mum.cs.utility;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;

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
    public static Gson receivePost(HttpServletRequest request) throws IOException {

        // 读取请求内容
        JsonReader jr = new JsonReader(new InputStreamReader(request.getInputStream(),"utf-8"));
//        String line = null;
//        StringBuilder sb = new StringBuilder();
//        while ((line = br.readLine()) != null) {
//            sb.append(line);
//        }
        //将json字符串转换为json对象
        Gson json=new Gson();
        json.fromJson(jr, Object.class);
        return json;
    }
}

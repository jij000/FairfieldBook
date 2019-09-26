package edu.mum.cs.controller;

import edu.mum.cs.utility.FBUtility;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@MultipartConfig  //使用MultipartConfig注解标注改servlet能够接受文件上传的请求
@WebServlet("/upload")
public class UploadFile extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        Part part = req.getPart("myfile");
        String disposition = part.getHeader("Content-Disposition"); // form-data; name="myfile"; filename="IMG_0011.JPG"
        System.out.println(disposition);
        String suffix = disposition.substring(disposition.lastIndexOf("."),disposition.length()-1).toLowerCase(); // .JPG
        //随机的生存一个32的字符串
        String filename = UUID.randomUUID()+suffix; // aa5bf1f3-bb84-404c-b12e-df93c71f4b91.JPG
        //获取上传的文件名
        InputStream is = part.getInputStream();
        //动态获取服务器的路径
        String serverPath = FBUtility.getImgPath(req.getServletContext());
        FileOutputStream fos = new FileOutputStream(serverPath + filename);
        byte[] bty = new byte[1024];
        int length =0;
        while((length=is.read(bty))!=-1){
            fos.write(bty,0,length);
        }
        fos.close();
        is.close();
        //拼接返回值域名路径（可修改）
        String hostPath = FBUtility.getServerUrl(req.getServletContext());
        String urlPath = hostPath + "/" + filename;
        resp.setContentType("application/json");
        resp.getWriter().println("{\"image\": \"" + urlPath + "\"}");
    }
}

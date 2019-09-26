package edu.mum.cs.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/*"})
public class AllFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest)servletRequest;
        HttpSession httpSession = httpServletRequest.getSession(false);
        String urlString = httpServletRequest.getRequestURL().toString().toUpperCase();

        if (urlString.indexOf("LOGIN")>-1||urlString.indexOf("REGI")>-1){
            filterChain.doFilter(servletRequest,servletResponse);
        }else {
            if (null==httpSession||null==httpSession.getAttribute("user")){
                ((HttpServletResponse)servletResponse).sendRedirect("/login.jsp");
            }else {
                filterChain.doFilter(servletRequest,servletResponse);
            }
        }
    }

    @Override
    public void destroy() {

    }
}
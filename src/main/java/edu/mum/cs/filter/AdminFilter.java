package edu.mum.cs.filter;


import edu.mum.cs.model.SystemRole;
import edu.mum.cs.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/Admin/*"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        User user = (User) ((HttpServletRequest) req).getSession().getAttribute("user");
        if (null == user || !SystemRole.ADMIN.equals(user.getRole())) {
            ((HttpServletResponse) res).sendRedirect("/login.jsp");
        } else {
            chain.doFilter(req, res);
        }
    }
    @Override
    public void destroy() {

    }
}
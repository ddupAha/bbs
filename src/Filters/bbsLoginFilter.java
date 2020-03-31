package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "bbsLoginFilter",urlPatterns = "/bbs_index.jsp")
public class bbsLoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession();
        if(session.getAttribute("b_is_login")==null)
        {
            response.sendRedirect("/cms_login.jsp");
            return ;
        }
        String flag =  session.getAttribute("b_is_login").toString();
        System.out.println("flag:"+flag);
        chain.doFilter(req, resp);

    }

    public void init(FilterConfig config) throws ServletException {

    }

}

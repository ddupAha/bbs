package Filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@WebFilter(filterName = "encodeFilter")
public class encodeFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpRequest request = (HttpRequest)req;
        HttpResponse response = (HttpResponse) resp;
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text-html;charset=UTF-8");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}

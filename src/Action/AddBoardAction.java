package Action;

import Dao.ManagerDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddBoardAction",urlPatterns = "/addBoard.jhtml")
public class AddBoardAction extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        ManagerDAO managerDAO=new ManagerDAO();
        String sName=request.getParameter("sName");
        int sType=Integer.parseInt(request.getParameter("sType"));
        int sParent=Integer.parseInt(request.getParameter("select"));
        String uName=request.getParameter("uName");
        if(sType==0){
            sParent=0;
        }
        managerDAO.addSection(sParent,sName,uName);
        response.sendRedirect("addBoard.jsp");
    }
}

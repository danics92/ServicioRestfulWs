package Controler;

import Dao.RolDaoImplement;
import Dao.UserDaoImplement;
import Pojo.Rol;
import Pojo.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;


/**
 * Created by blackwidow on 13/12/16.
 */
public class RolControler extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        Enumeration paramNames = request.getParameterNames();
        RolDaoImplement rolDao = new RolDaoImplement();
        RequestDispatcher rd = request.getRequestDispatcher("/rol.jsp");
        if(!paramNames.hasMoreElements()){
            try {
                List<Rol> roles = rolDao.getRoles();
                request.setAttribute("roles", roles);
                rd.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues[0] != "" && paramName.equals("delete")) {
                    try {
                        rolDao.deleteRol(paramValues[0]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    throw new ServletException();
                }
            }
            response.sendRedirect("/Rol");
        }
    }

    public void doPost(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        String rol_name = request.getParameter("rol_name");
        String rol_desc = request.getParameter("rol_desc");
        try {
            RolDaoImplement rolDao = new RolDaoImplement();
            rolDao.insertRol(rol_name,rol_desc);
            request.setAttribute("roles", null);
            response.sendRedirect("/Rol");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

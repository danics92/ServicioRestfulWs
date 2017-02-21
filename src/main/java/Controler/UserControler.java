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
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by blackwidow on 30/11/16.
 */
public class UserControler extends HttpServlet {

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        UserDaoImplement UseDao = null;
        try {
            UseDao = new UserDaoImplement();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        RolDaoImplement RolDao = new RolDaoImplement();
        Enumeration paramNames = request.getParameterNames();
        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        List<User> usuarios;
        if (paramNames.hasMoreElements()) {
            while (paramNames.hasMoreElements()) {
                String paramName = (String) paramNames.nextElement();
                String[] paramValues = request.getParameterValues(paramName);
                if (paramValues[0] != "" && paramName.equals("delete")) {
                    try {
                        UseDao.deleteUser(paramValues[0]);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else {
                    throw new ServletException();
                }
            }
        }else {
            try {
                usuarios = UseDao.getUsers();
                List<Rol> roles = RolDao.getRoles();
                request.setAttribute("user", usuarios);
                request.setAttribute("check_rol", roles);
                rd.forward(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        String user_name = request.getParameter("nombre");
        String user_pass = request.getParameter("password");
        String[] roles = request.getParameterValues("rol");
        //ArrayList<Rol> l_roles = new Arrays.asList(roles);
        try {
            UserDaoImplement userDao = new UserDaoImplement();
            //userDao.insertUser(user_name, user_pass, Arrays.asList(roles));
            response.sendRedirect("/index");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

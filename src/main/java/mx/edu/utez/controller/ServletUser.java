package mx.edu.utez.controller;

import mx.edu.utez.model.person.BeanPerson;
import mx.edu.utez.model.role.BeanRole;
import mx.edu.utez.model.user.BeanUser;
import mx.edu.utez.model.user.DaoUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "ServletUser", value = "/ServletUser")
public class ServletUser extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ServletUser.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listUsers", new DaoUser().findAll());
        request.getRequestDispatcher("/views/user/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        String name = request.getParameter("name") != null ? request.getParameter("name") : "";
        String lastname = request.getParameter("lastname");
        String age = request.getParameter("age");

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        int edad = 0;
        int rol = 0;
        try{
            edad = Integer.parseInt(age);
            rol = Integer.parseInt(role);
        }catch(Exception e){
            logger.error("Ha ocurrido un error: " + e.getMessage());
        }

        switch(action){
            case "create":
                // do something
                BeanRole beanRole = new BeanRole(rol, "");
                BeanPerson beanPerson = new BeanPerson(0, name, lastname, edad);
                BeanUser beanUser = new BeanUser(0, email, password, 0, beanPerson, beanRole);

                if(new DaoUser().create(beanUser)){
                    request.setAttribute("message", "Usuario registrado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no registrado");
                }

                doGet(request, response);
                break;
            case "update":
                // do something
                break;
            case "delete":
                // do something
                break;
            default:
                // no supported
                break;
        }
    }
}

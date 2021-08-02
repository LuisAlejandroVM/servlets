package mx.edu.utez.controller;

import com.google.gson.Gson;
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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "ServletUser", urlPatterns = {"/readUsers", "/createUser", "/getUserById", "/findById", "/updateUser", "/deleteUser"})
public class ServletUser extends HttpServlet {
    Logger logger = LoggerFactory.getLogger(ServletUser.class);
    BeanUser beanUser = new BeanUser();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listUsers", new DaoUser().findAll());
        request.getRequestDispatcher("/views/user/users.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map map = new HashMap();
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");

        switch(action){
            case "create":
                // do something
                String name = request.getParameter("name") != null ? request.getParameter("name") : "";
                String lastname = request.getParameter("lastname");
                int age = Integer.parseInt(request.getParameter("age"));

                String email = request.getParameter("email");
                String password = request.getParameter("password");
                int role = Integer.parseInt(request.getParameter("role"));

                BeanRole beanRole = new BeanRole(role, "");
                BeanPerson beanPerson = new BeanPerson(0, name, lastname, age);
                BeanUser beanUser = new BeanUser(0, email, password, 0, beanPerson, beanRole);

                if(new DaoUser().create(beanUser)){
                    request.setAttribute("message", "Usuario registrado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no registrado");
                }

                doGet(request, response);
                break;

            case "getUserById":
                // do something
                long id = Long.parseLong(request.getParameter("id"));
                request.setAttribute("user", new DaoUser().findById(id));
                request.getRequestDispatcher("/views/user/update.jsp").forward(request, response);
                break;
            case "findById":
                // do something
                try {
                    Gson gson = new Gson();
                    long id5 = Long.parseLong(request.getParameter("id"));

                    map.put("user", new DaoUser().findById(id5));

                    response.setStatus(200);
                }catch(Exception e){
                    response.setStatus(400);
                    logger.error("Usuario no encontrado.");
                }
                write(response, map);
                break;
            case "update":
                // do something
                String name1 = request.getParameter("name") != null ? request.getParameter("name") : "";
                String lastname1 = request.getParameter("lastname");
                int age1 = Integer.parseInt(request.getParameter("age"));

                long id1 = Long.parseLong(request.getParameter("id"));
                String email1 = request.getParameter("email");
                String password1 = request.getParameter("password");
                int role1 = Integer.parseInt(request.getParameter("role"));

                BeanRole beanRole1 = new BeanRole(role1, "");
                BeanPerson beanPerson1 = new BeanPerson(0, name1, lastname1, age1);
                BeanUser beanUser1 = new BeanUser(id1, email1, password1, 0, beanPerson1, beanRole1);

                if(new DaoUser().update(beanUser1)){
                    request.setAttribute("message", "Usuario modificado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no modificado");
                }

                doGet(request, response);
                break;
            case "delete":
                // do something
                long id2 = Long.parseLong(request.getParameter("id"));
                if(new DaoUser().delete(id2)){
                    request.setAttribute("message", "Usuario eliminado correctamente");
                } else {
                    request.setAttribute("message", "Usuario no eliminado");
                }
                doGet(request, response);
                break;
            default:
                // no supported
                break;
        }
    }

    private void write(HttpServletResponse response, Map<String, Object> map) throws IOException{
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(new Gson().toJson(map));
    }
}


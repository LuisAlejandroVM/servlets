package mx.edu.utez.model.user;

import mx.edu.utez.model.person.BeanPerson;
import mx.edu.utez.model.role.BeanRole;
import mx.edu.utez.service.ConnectionMySQL;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoUser {
    Connection con;
    CallableStatement cstm;
    ResultSet rs;

    public List<BeanUser> findAll(){
        List<BeanUser> listUsers = new ArrayList<>();
        try {
            // SELECT * FROM users AS U INNER JOIN persons AS P ON U.idPerson = P.id INNER JOIN roles AS R ON U.idRole = R.id;
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_findAll}");
            rs = cstm.executeQuery();

            while(rs.next()){
                BeanRole role = new BeanRole();
                BeanPerson person = new BeanPerson();
                BeanUser user = new BeanUser();

                role.setId(rs.getShort("id"));
                role.setDescription(rs.getString("name"));

                person.setId(rs.getLong("id"));
                person.setName(rs.getString("name"));
                person.setLastname(rs.getString("lastname"));
                person.setEdad(rs.getShort("age"));

                user.setId(rs.getLong("id"));
                user.setEmail(rs.getString("email"));
                user.setPassword(rs.getString("password"));

                user.setIdPerson(person);
                user.setIdRole(role);

                listUsers.add(user);
            }
        }catch (SQLException e){
            System.out.println("Ha ocurrido un error: " + e.getMessage());
        } finally {
            try{
                if(con != null){
                    con.close();
                }
                if(rs != null){
                    rs.close();
                }
            }catch(SQLException e){ }
        }
        return listUsers;
    }

    public boolean create(BeanUser user){
        boolean flag = false;
        try{
            con = ConnectionMySQL.getConnection();
            cstm = con.prepareCall("{call sp_insertUser(?,?,?,?,?)}");
            cstm.setString(1, user.getIdPerson().getName());
            cstm.setString(2, user.getIdPerson().getLastname());
            // Terminar de poner los demás atributos

            flag = cstm.execute();
        }catch(SQLException e){
            System.out.println("Error: " + e.getMessage());
        } finally {
            try{
                if(con != null){
                    con.close();
                }
                if(cstm != null){
                    cstm.close();
                }
            }catch(SQLException e){ }
        }
        return flag;
    }
}

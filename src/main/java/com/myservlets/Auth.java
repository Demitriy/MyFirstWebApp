package com.myservlets;

import database.CRUD;
import database.Root;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;

@WebServlet("/auth")
public class Auth extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        Connection connection = null;
        try {
            //Simple connection
            //Class.forName("com.mysql.jdbc.Driver"); //Important!
            //connection = DriverManager.getConnection(Root.URL.toString(), Root.USERNAME.toString(), Root.PASSWORD.toString());

            //JNDI

            Context initContext= new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/simplewebapp");
            //System.out.println(ds);
            connection = ds.getConnection();
            //System.out.println(ds);

            if (connection == null) return;
            if (checkAuth(connection, req.getParameter("login"), req.getParameter("password"))) {
                CRUD crud = new CRUD(connection);
                req.setAttribute("list", crud.read());
                req.getRequestDispatcher("/pages/page.jsp").forward(req, resp);
            } else {
                req.setAttribute("flag", true);
                req.getRequestDispatcher("/index.jsp").forward(req, resp);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("Че то пошло не так");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ебать че то не то");
            e.printStackTrace();
        }
    }

    private boolean checkAuth(Connection connection, String login, String password) throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        while (resultSet.next()) {
            String user = resultSet.getString("user");
            String pass = resultSet.getString("password");
            if (user.equalsIgnoreCase(login) && pass.equals(password)) {
                resultSet.close();
                statement.close();
                return true;
            }
        }
        return false;
    }
}
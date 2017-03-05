package com.myservlets;

import database.*;

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


@WebServlet(urlPatterns = "/page/*")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        req.setAttribute("list2", req.getParameterNames());

        Connection connection = null;

        try {
            //connection = DriverManager.getConnection(Root.URL.toString(), Root.USERNAME.toString(), Root.PASSWORD.toString());

            Context initContext = new InitialContext();
            DataSource ds = (DataSource) initContext.lookup("java:comp/env/jdbc/simplewebapp");
            connection = ds.getConnection();

            CRUD crud = new CRUD(connection);
            if (req.getParameter("CUDbutton") != null) crud.create(req);
            req.setAttribute("list", crud.read());
            req.getRequestDispatcher("/pages/page.jsp").forward(req, resp);


            connection.close();
        } catch (SQLException e) {
            System.out.println("Че то пошло не так2");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Ебать че то не то2");
        }
    }

}

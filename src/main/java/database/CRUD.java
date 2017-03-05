package database;

import javax.servlet.http.HttpServletRequest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CRUD {

    private Connection connection;

    public CRUD(Connection connection) {
        this.connection = connection;
    }

    public List<List<String>> read() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<List<String>> table = new ArrayList<List<String>>();
        while (resultSet.next()) {
            List<String> row = new ArrayList<String>();
            String user = resultSet.getString("user");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name") == null ? "" : resultSet.getString("name");
            String surname = resultSet.getString("surname") == null ? "" : resultSet.getString("surname");
            String number = resultSet.getString("number") == null ? "" : resultSet.getString("number");
            row.add(user);
            row.add(password);
            row.add(name);
            row.add(surname);
            row.add(number);
            table.add(row);
        }
        resultSet.close();
        statement.close();
        return table;
    }

    public void create(HttpServletRequest req) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users VALUE(?,?,?,?,?)");
        preparedStatement.setString(1, req.getParameter("CUDuser"));
        preparedStatement.setString(2, req.getParameter("CUDpassword"));
        preparedStatement.setString(3, req.getParameter("CUDname"));
        preparedStatement.setString(4, req.getParameter("CUDsurname"));
        preparedStatement.setString(5, req.getParameter("CUDnumber"));
        preparedStatement.execute();
    }

    public void update() throws SQLException  {

    }

    public void delete() throws SQLException  {

    }
}

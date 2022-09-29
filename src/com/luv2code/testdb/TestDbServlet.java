package com.luv2code.testdb;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;

@WebServlet(name = "TestDbServlet")
public class TestDbServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //setup connection variables
        String user = "springstudent";
        String password = "springstudent";
        String jdbcUrl= "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false&amp;serverTimezone=UTC";
        String driver = "com.mysql.jdbc.Driver";

        //get connection to db
        try {
            PrintWriter out = response.getWriter();
            out.println("Connecting to db: " + jdbcUrl);
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(jdbcUrl, user,password);
            out.println("SUCCESS!!!");
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException(e);
        } finally {

        }
    }
}

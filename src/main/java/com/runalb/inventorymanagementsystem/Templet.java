package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "Templet", value = "/dashboard/Templet")
public class Templet extends HttpServlet {

//    public Templet(){
//        try{
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory_management_system_db", "root","mysql123");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        PrintWriter out = response.getWriter();
//        try{
//            Statement stmt=con.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT * FROM available_products_table");
//
//            while(rs.next())
//            {
//                out.println(" "+rs.getInt(1)+" "+rs.getString(2)+" "+rs.getInt(3));
//            }
//
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html> <html lang=\"en\">");
        out.println("<head> " +
                        "<meta charset=\"UTF-8\"> " +
                        "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                        // Add Page Title here
                        "<title>View Available Products - IMS</title> " +
                        "<link rel=\"stylesheet\" href=\"../bootstrap-4.6.2/css/bootstrap.css\"> " +
                        "<link rel=\"stylesheet\" href=\"../css/style.css\"> " +
                    "</head>");

        out.println("<body>");

        // nav bar code
        out.println("<div class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\"> " +
                        "<a class=\"navbar-brand my-0 mr-md-auto\" href=\"../index.html\"> " +
                            "<img src=\"../img/logo.svg\" alt=\"logo\" width=\"130\" height=\"30\" alt=\"Logo\" loading=\"lazy\"> " +
                        "</a> " +
                        "<button class=\"btn btn-outline logout-btn\" onclick=\"location.href='../login.html'\">Login</button> " +
                    "</div>");

        // block content
        out.println("<div class=\"container h-100\"> " +
                        "<div class=\"row align-items-center h-100\" > " +
                            "<div class=\"col-11 mx-auto\"> " +
                                "<div class=\"mt-4\"> " +
                                    "<div class=\"text-center p-1\"> " +
                                        // Add Title here
                                        "<h3 class=\"theme-color\">View Available Products</h3> " +
                                    "</div> " +
                                "</div>" );

        // dashboard content
        out.println(            "<h1 class='text-center'>Templet....</h1>");

        out.println(        "</div>" +
                        "</div>" +
                    "</div>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

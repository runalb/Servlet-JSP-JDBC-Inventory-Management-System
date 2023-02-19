package com.runalb.inventorymanagementsystem;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AddProducts", urlPatterns = "/Dashboard/AddProducts")

public class AddProducts extends HttpServlet {
    Connection con=null;
    public AddProducts(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con= DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/inventory_management_system_db", "root","mysql123");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // HttpSession
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String username = (String) session.getAttribute("username");

        out.println("<!DOCTYPE html> <html lang=\"en\">");
        out.println("<head> " +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                // Add Page Title here
                "<title>Add Products - IMS</title> " +
                "<link rel=\"stylesheet\" href=\"../bootstrap-4.6.2/css/bootstrap.css\"> " +
                "<link rel=\"stylesheet\" href=\"../css/style.css\"> " +
                "</head>");

        out.println("<body>");

        // nav bar code - dashboard content
        if (username != null){
            out.println("<div class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\"> " +
                    "<a class=\"navbar-brand my-0 mr-md-auto\" href=\"../Dashboard\"> " +
                    "<img src=\"../img/logo.svg\" alt=\"logo\" width=\"130\" height=\"30\" alt=\"Logo\" loading=\"lazy\"> " +
                    "</a> " +
                    "<button class=\"btn btn-outline logout-btn\" onclick=\"location.href='../Logout'\">Logout ("+username+")</button> " +
                    "</div>");
        } else {
            out.println("<div class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\"> " +
                    "<a class=\"navbar-brand my-0 mr-md-auto\" href=\"../index.html\"> " +
                    "<img src=\"../img/logo.svg\" alt=\"logo\" width=\"130\" height=\"30\" alt=\"Logo\" loading=\"lazy\"> " +
                    "</a> " +
                    "<button class=\"btn btn-outline logout-btn\" onclick=\"location.href='../login.html'\">Dashboard Login</button> " +
                    "</div>");
        }

        // block content
        out.println("<div class=\"container h-100\"> " +
                    "<div class=\"row align-items-center h-100\" > " +
                    "<div class=\"col-9 mx-auto\"> " +
                    "<div class=\"mt-4\"> " +
                    "<div class=\"text-center p-1\"> " +
                    // Add Title here
                    "<h3 class=\"theme-color\">Add Products</h3> " +
                    "</div> " +
                "</div>" );

        // dashboard content
        out.println("<form action=\"AddProductsResult\" method=\"GET\"> " +
                        "<div class=\"form-group mx-4 mt-4\"> " +
                            "<input type=\"number\" required name=\"productid\" class=\"form-control\" placeholder=\"Product ID\"> " +
                        "</div> " +
                        "<div class=\"form-group mx-4 mt-4\"> " +
                            "<input type=\"text\" required name=\"productname\" class=\"form-control\" placeholder=\"Product Name\"> " +
                        "</div> " +
                        "<div class=\"form-group mx-4 mt-4\"> " +
                            "<input type=\"number\" required name=\"productprice\" class=\"form-control\" placeholder=\"Product Price\"> " +
                        "</div> " +
                        "<div class=\"form-group mx-4 mt-4\"> " +
                            "<input type=\"number\" required name=\"productqty\" class=\"form-control\" placeholder=\"Quantity\"> " +
                        "</div> " +
                        "<div class=\"form-group mx-4 mt-4\"> " +
                            "<button type=\"submit\" class=\"btn login-btn btn-block \">Add Product</button> " +
                        "</div> " +
                    "</form>");

        out.println(        "<br>"+
                        "</div>" +
                    "</div>" +
                "</div>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

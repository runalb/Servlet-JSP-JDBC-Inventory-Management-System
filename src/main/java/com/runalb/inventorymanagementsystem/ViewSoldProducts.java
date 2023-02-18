package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "ViewSoldProducts", urlPatterns = "/Dashboard/ViewSoldProducts")
public class ViewSoldProducts extends HttpServlet {
    Connection con=null;
    public ViewSoldProducts(){
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
                        "<title>View Sold Products - IMS</title> " +
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
                "<div class=\"col-11 mx-auto\"> " +
                "<div class=\"mt-4\"> " +
                "<div class=\"text-center p-1\"> " +
                // Add Title here
                "<h3 class=\"theme-color\">View Sold Products</h3> " +
                "</div> " +
                "</div>" );

        // dashboard content
        try{
            Statement stmt=con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("SELECT * FROM sold_products_table");

            if (rs.next()){
                out.println("<table class='table table-striped text-center'>");
                    out. println("<thead>" +
                                    "<tr>" +
                                        "<th scope='col'>Date Time</th> " +
                                        "<th scope='col'>Product ID</th> " +
                                        "<th scope='col'>Product Name</th> " +
                                        "<th scope='col'>Product Price</th> " +
                                        "<th scope='col'>Sold Quantity</th>" +
                                    "</tr>" +
                                "</thead>");

                    out.println("<tbody>");
                    rs.beforeFirst();
                    while(rs.next()) {
                        out.println("<tr> " +
                                        "<td>"+rs.getString(1)+"</th> " +
                                        "<td>"+rs.getInt(2)+"</td>" +
                                        "<td>"+rs.getString(3)+"</td>" +
                                        "<td>"+rs.getInt(4)+"</td>" +
                                        "<td>"+rs.getInt(5)+"</td>" +
                                    "</tr>");
                    }
                    out.println("</tbody>");
                out.println("</table>");

            } else {
                out.println("<h1 class='text-center'>No Products are their to view....</h1>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Back btn - Dashboard
        out.println("<button class='btn action-btn btn-block' onclick=\"location.href='../Dashboard'\">Go Back</button>");


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

package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "SearchAvailableProductsResult", value = "/dashboard/SearchAvailableProductsResult")
public class SearchAvailableProductsResult extends HttpServlet {
    Connection con=null;
    public SearchAvailableProductsResult(){
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

        // getParameter values from HTML form
        String searchQuery = request.getParameter("searchquery");


        out.println("<!DOCTYPE html> <html lang=\"en\">");
        out.println("<head> " +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                // Add Page Title here
                "<title>Result - Search Available Products - IMS</title> " +
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
                "<h3 class=\"theme-color\">Search Result</h3> " +
                "</div> " +
                "</div>" );

        // dashboard content
        try{
            PreparedStatement ps = con.prepareStatement("SELECT * FROM available_products_table WHERE product_id=? OR product_name=?",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setString(1,searchQuery);
            ps.setString(2,searchQuery);
            ResultSet rs = ps.executeQuery();

            if (rs.next()){
                out.println("<table class='table table-striped text-center'>");
                out. println("<thead>" +
                        "<tr>" +
                        "<th scope='col'>Product ID</th>" +
                        "<th scope='col'>Product Name</th>" +
                        "<th scope='col'>Product Price</th>" +
                        "<th scope='col'>Quantity</th>" +
                        "<th scope='col'>Action</th>" +
                        "</tr>" +
                        "</thead>");
                    out.println("<tbody>");

                    rs.beforeFirst();

                    while(rs.next()) {
                        out.println("<tr> " +
                                "<td>"+rs.getInt(1)+"</th> " +
                                "<td>"+rs.getString(2)+"</td>" +
                                "<td>"+rs.getInt(3)+"</td>" +
                                "<td>"+rs.getInt(4)+"</td>" +
                                "<td > " +
                                "<a href=\"{% url 'dashboard:update' item.id %}\"> " +
                                "<button type=\"button\" class=\"btn action-btn\">Update</button> " +
                                "</a> " +
                                "<a href=\"{% url 'dashboard:delete' item.id %}\"> " +
                                "<button type=\"button\" class=\"btn btn-danger\">Delete</button> " +
                                "</a> " +
                                "</td> " +
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

        out.println("<button class='btn action-btn btn-block' onclick=\"location.href='index.html'\">Go Back</button>");


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

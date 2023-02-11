package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "AddProductsResult", value = "/dashboard/AddProductsResult")

public class AddProductsResult extends HttpServlet {
    Connection con=null;
    public AddProductsResult(){
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
        int productId = Integer.parseInt(request.getParameter("productid"));
        String productName = request.getParameter("productname");
        int productPrice = Integer.parseInt(request.getParameter("productprice"));
        int productQty = Integer.parseInt(request.getParameter("productqty"));

        out.println("<!DOCTYPE html> <html lang=\"en\">");
        out.println("<head> " +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                // Add Page Title here
                "<title>Result - Add Products - IMS</title> " +
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
                    "<h3 class=\"theme-color\">Add Products</h3> " +
                    "</div> " +
                "</div>" );

        // dashboard content
        try{
            PreparedStatement ps = con.prepareStatement("INSERT INTO available_products_table VALUES (?,?,?,?)",ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps.setInt(1,productId);
            ps.setString(2,productName);
            ps.setInt(3,productPrice);
            ps.setInt(4,productQty);

            try {
                int i = ps.executeUpdate();

                if (i > 0) {
                    out.println("<h1 class='text-center'>New Product Added successfully</h1><br><br>");

                    out.println("<table class='table table-striped text-center'>" +
                            "<thead>" +
                            "<tr>" +
                            "<th scope='col'>Product ID</th>" +
                            "<th scope='col'>Product Name</th>" +
                            "<th scope='col'>Product Price</th>" +
                            "<th scope='col'>Quantity</th>" +
                            "</tr>" +
                            "</thead>" +
                            "<tbody>" +
                            "<tr>" +
                            "<td>" + productId + "</th> " +
                            "<td>" + productName + "</th> " +
                            "<td>" + productPrice + "</th> " +
                            "<td>" + productQty + "</th> " +
                            "</tr>" +
                            "</tbody>" +
                            "</table>");

                    out.println("<button class='btn action-btn btn-block' onclick=\"location.href='index.html'\">Go Back</button>");
                }

            } catch (SQLIntegrityConstraintViolationException e){
                out.println("<h1 class='text-center'>Product ID Already Taken!!!</h1>");
                out.println("<button class='btn action-btn btn-block' onclick=\"location.href='add-products.html'\">Go Back</button>");
            }

            ps.clearParameters();

        } catch (SQLException e) {
            out.println("<h1 class='text-center'>Something went wrong! </h1>");
            out.println("<button class='btn action-btn btn-block' onclick=\"location.href='add-products.html'\">Go Back</button>");
            e.printStackTrace();
        }

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

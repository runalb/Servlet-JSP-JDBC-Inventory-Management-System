package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "SellAvailableProductsResult", value = "/dashboard/SellAvailableProductsResult")
public class SellAvailableProductsResult extends HttpServlet {
    Connection con=null;
    public SellAvailableProductsResult(){
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
        int sellProductId = Integer.parseInt(request.getParameter("product_id"));
        int sellQty = Integer.parseInt(request.getParameter("sellqty"));

        out.println("<!DOCTYPE html> <html lang=\"en\">");
        out.println("<head> " +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                // Add Page Title here
                "<title>Result - Sell Available Products - IMS</title> " +
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
                "<h3 class=\"theme-color\">Result - Sell Available Products</h3> " +
                "</div> " +
                "</div>" );

        // dashboard content
        try{
            PreparedStatement ps1 = con.prepareStatement("SELECT * FROM available_products_table WHERE product_id=? AND quantity>=?", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ps1.setInt(1,sellProductId);
            ps1.setInt(2,sellQty);

            ResultSet rs1 = ps1.executeQuery();
            ps1.clearParameters();

            if(rs1.next()){
                int rs1ProductId = rs1.getInt(1);
                String rs1ProductName = rs1.getString(2);
                int rs1ProductPrice = rs1.getInt(3);
                int rs1ProductQuantity =  rs1.getInt(4);

//                out.println(" " + rs1ProductId);
//                out.println(" " + rs1ProductName);
//                out.println(" " + rs1ProductPrice);
//                out.println(" " + rs1ProductQuantity);

                PreparedStatement ps2 = con.prepareStatement("UPDATE available_products_table SET quantity=? WHERE product_id=?", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

                int RemProductQty = rs1ProductQuantity - sellQty;
                ps2.setInt(1,RemProductQty);
                ps2.setInt(2,sellProductId);

                // subtracting sold product qty from available_products_table
                int i2 = ps2.executeUpdate();

                if(i2>0){
//                    out.println(i2+" successfully subtracted product qty from available_products_table");
                    PreparedStatement ps3 = con.prepareStatement("INSERT INTO sold_products_table (product_id,product_name,product_price,quantity) VALUES (?,?,?,?)", ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                    ps3.setInt(1,rs1ProductId);
                    ps3.setString(2,rs1ProductName);
                    ps3.setInt(3,rs1ProductPrice);
                    ps3.setInt(4,sellQty);

                    int i3 = ps3.executeUpdate();

                    if (i3>0){
//                        out.println(i3+"Sold Product added to sold_products_table...!!!");
                        out.println("<h1 class='text-center m-4'>Product sold successfully!</h1><br>");

//                        Sold product info in table
                        out.println("<table class='table table-striped text-center'>" +
                                "<thead>" +
                                "<tr>" +
                                "<th scope='col'>Product ID</th>" +
                                "<th scope='col'>Product Name</th>" +
                                "<th scope='col'>Product Price</th>" +
                                "<th scope='col'>Sold Quantity</th>" +
                                "</tr>" +
                                "</thead>" +
                                "<tbody>" +
                                "<tr>" +
                                "<td>" + rs1ProductId + "</th> " +
                                "<td>" + rs1ProductName + "</th> " +
                                "<td>" + rs1ProductPrice + "</th> " +
                                "<td>" + sellQty + "</th> " +
                                "</tr>" +
                                "</tbody>" +
                                "</table>");

                        out.println("<button class='btn action-btn btn-block' onclick=\"location.href='index.html'\">Go Back</button>");

                    } else {
//                        out.println(" ERRR on ps3 - Adding product to sold_products_table ");
                        out.println("<h1 class='text-center'>Something went wrong! </h1>");

                        out.println("<button class='btn action-btn btn-block' onclick=\"location.href='SellAvailableProducts'\">Go Back</button>");
                    }
                    ps3.clearParameters();



                } else {
//                    out.println(i2 + "ERRR on ps2 - subtracting sold product qty from available_products_table ");
                    out.println("<h1 class='text-center'>Something went wrong! </h1>");
                    out.println("<button class='btn action-btn btn-block' onclick=\"location.href='SellAvailableProducts'\">Go Back</button>");
                }

                ps2.clearParameters();


            } else {
//                out.println("Errr on ps1 - getting data from available_products_table");
                out.println("<h3 class='text-center m-4'>Entered Quantity is greater than available stock <br><u>or</u><br> Product is Out of Stock!</h3>");
                out.println("<button class='btn action-btn btn-block' onclick=\"location.href='SellAvailableProducts'\">Go Back</button>");

            }


        } catch (SQLException e) {
            out.println("<h1 class='text-center'>Something went wrong! </h1>");
            out.println("<button class='btn action-btn btn-block' onclick=\"location.href='SellAvailableProducts'\">Go Back</button>");
            e.printStackTrace();
        }


        out.println(        "<br>"+
                        "</div>" +
                    "</div>" +
                "</div>");

        out.println("</body>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

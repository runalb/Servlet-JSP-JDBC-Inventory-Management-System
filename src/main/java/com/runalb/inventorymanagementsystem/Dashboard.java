package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Dashboard", urlPatterns = "/Dashboard")
public class Dashboard extends HttpServlet {
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
                "<title>DASHBOARD SERVELET Dashboard - IMS</title> " +
                "<link rel=\"stylesheet\" href=\"bootstrap-4.6.2/css/bootstrap.css\"> " +
                "<link rel=\"stylesheet\" href=\"css/style.css\"> " +
                "</head>");

        out.println("<body>");

        // nav bar code
        if (username != null){
            out.println("<div class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\"> " +
                    "<a class=\"navbar-brand my-0 mr-md-auto\" href=\"Dashboard\"> " +
                    "<img src=\"img/logo.svg\" alt=\"logo\" width=\"130\" height=\"30\" alt=\"Logo\" loading=\"lazy\"> " +
                    "</a> " +
                    "<button class=\"btn btn-outline logout-btn\" onclick=\"location.href='Logout'\">Logout ("+username+")</button> " +
                    "</div>");
        } else {
            out.println("<div class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\"> " +
                    "<a class=\"navbar-brand my-0 mr-md-auto\" href=\"index.html\"> " +
                    "<img src=\"img/logo.svg\" alt=\"logo\" width=\"130\" height=\"30\" alt=\"Logo\" loading=\"lazy\"> " +
                    "</a> " +
                    "<button class=\"btn btn-outline logout-btn\" onclick=\"location.href='login.html'\">Dashboard Login</button> " +
                    "</div>");
        }

        out.println("<div class=\"mt-4\"> " +
                        "<div class=\"text-center p-3\"> " +
                            "<h2 class=\"theme-color\">Dashboard</h2> " +
                            "</div> " +
                        "</div> ");

        out.println("<div class=\"container\"> ");

            out.println("<div class=\"row my-4\"> ");
                out.println("<div class=\"col-sm\"> " +
                                "<button class=\"btn btn-block action-btn p-3\" onclick=\"location.href='add-products.html'\"> " +
                                     "<i class=\"far fa-plus-square pr-3\"></i> Add Products " +
                                "</button> " +
                            "</div> ");

                out.println("<div class=\"col-sm\"> " +
                                "<button class=\"btn btn-block action-btn p-3\" onclick=\"location.href='Dashboard/ViewAvailableProducts'\"> " +
                                     "<i class=\"fas fa-boxes pr-3\"></i> View Available Products " +
                                "</button> " +
                            "</div> ");

                out.println("<div class=\"col-sm\"> " +
                                "<button class=\"btn btn-block action-btn p-3\" onclick=\"location.href='search-available-products.html'\"> " +
                                    "<i class=\"fas fa-search pr-3\"></i> Search Products " +
                                "</button> " +
                            "</div> ");
            out.println("</div> ");


            out.println("<div class=\"row my-4\"> ");
                out.println("<div class=\"col-sm\"> " +
                                "<button class=\"btn btn-block action-btn p-3\" onclick=\"location.href='Dashboard/SellAvailableProducts'\"> " +
                                    "<i class=\"far fa-minus-square pr-3\"></i> Sell Products " +
                                "</button> " +
                            "</div>");

                out.println("<div class=\"col-sm\"> " +
                                "<button class=\"btn btn-block action-btn p-3\" onclick=\"location.href='Dashboard/ViewSoldProducts'\"> " +
                                    "<i class=\"fas fa-boxes pr-3\"></i> View Sold Products " +
                                "</button> " +
                            "</div> ");


                out.println("<div class=\"col-sm\"> " +
                                "<button class=\"btn btn-block action-btn p-3\" onclick=\"location.href='Dashboard/ViewUsers'\"> " +
                                    "<i class=\"fas fa-user-shield pr-3\"></i> Users " +
                                "</button> " +
                            "</div> ");
            out.println("</div> ");

        out.println("</div> ");


        out.println("</body>");
        out.println("</html>");


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

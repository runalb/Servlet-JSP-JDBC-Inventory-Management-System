package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "Logout", urlPatterns = "/Logout")
public class Logout extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // HttpSession
        HttpSession session = request.getSession();
        String sessionId = session.getId();
        String username = (String) session.getAttribute("username");

        // IMP - invalidate session after user logout
        session.invalidate();

        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html> <html lang=\"en\">");
        out.println("<head> " +
                "<meta charset=\"UTF-8\"> " +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"> " +
                // Add Page Title here
                "<title>Logout - IMS</title> " +
                "<link rel=\"stylesheet\" href=\"bootstrap-4.6.2/css/bootstrap.css\"> " +
                "<link rel=\"stylesheet\" href=\"css/style.css\"> " +
                "</head>");

        out.println("<body>");


        // nav bar code
        out.println("<div class=\"d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 bg-white border-bottom shadow-sm\"> " +
                    "<a class=\"navbar-brand my-0 mr-md-auto\" href=\"index.html\"> " +
                    "<img src=\"img/logo.svg\" alt=\"logo\" width=\"130\" height=\"30\" alt=\"Logo\" loading=\"lazy\"> " +
                    "</a> " +
                    "<button class=\"btn btn-outline logout-btn\" onclick=\"location.href='login.html'\">Dashboard Login</button> " +
                    "</div>");


        // block content
        out.println("<div class=\"container h-100\"> " +
                "<div class=\"row align-items-center h-100\" > " +
                "<div class=\"col-11 mx-auto\"> " +
                "<div class=\"mt-4\"> " +
                "<div class=\"text-center p-1\"> " +
                // Add Title here
                "<h3 class=\"theme-color\">Logout Successful</h3> " +
                "</div> " +
                "</div>" );


        out.println("<h3 class='text-center m-4'>"+username+", You have successfully logged out. </h3>");
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

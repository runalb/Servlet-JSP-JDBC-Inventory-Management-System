package com.runalb.inventorymanagementsystem;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "Login", urlPatterns = "/LoginResult")
public class LoginResult extends HttpServlet {
    Connection con=null;
    public LoginResult(){
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
        String uname=request.getParameter("username");
        String pswd=request.getParameter("password");

        // RequestDispatcher
        RequestDispatcher rd1=request.getRequestDispatcher("/Dashboard");
        RequestDispatcher rd2=request.getRequestDispatcher("login.html");

        // HttpSession
        HttpSession session = request.getSession();

        // dashboard content
        try {
            PreparedStatement ps = con.prepareStatement("SELECT user_username,user_password FROM users_table WHERE user_username=? and user_password=?", ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ps.setString(1, uname);
            ps.setString(2, pswd);
            ResultSet rs = ps.executeQuery();
            ps.clearParameters();

            if(rs.next())
            {
                // set Http Session Attribute username
                session.setAttribute("username",uname);
                response.sendRedirect("Dashboard");
            }

            else
            {
                out.println("<script> let errMsg = \"<i>Invalid Username or Password!</i>\";</script>");
                response.setContentType("text/html;charset=UTF-8");
                rd2.include(request, response);
            }


        } catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }
}

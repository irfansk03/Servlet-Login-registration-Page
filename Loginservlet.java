package com.tech;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Loginservlet extends HttpServlet{
	
	public Loginservlet() {
		System.out.println("inside the Loginservlet constructor ");
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name=req.getParameter("uname1");
		String pass=req.getParameter("pass1");
		
		System.out.println("-----Name is-----"+name);
		System.out.println("-----password is-----"+pass);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/user_db","root","Pass@123");
			PreparedStatement ps = con.prepareStatement("select * from user_db.student where uname= ? and pass=?");

			ps.setString(1, name);
			ps.setString(2, pass);

			ResultSet rs = ps.executeQuery();
			System.out.println(rs);
			if (rs.next()) {
				System.out.println("--success case----");
				RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
				rd.forward(req, resp);
			} else {
				System.out.println("--error case----");
				RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
				rd.forward(req, resp);
			}

			
			
			
		}catch(Exception ex) {
			System.out.println("inside exception case-->");
			ex.printStackTrace();
		}
//		
//		if(name.equals("admin")&&pass.equals("admin123"))
//		{
//			
//			System.out.println("------Success Case------");
//			RequestDispatcher rd = req.getRequestDispatcher("Success.jsp");
//			rd.forward(req, resp);
//		}else {
//			System.out.println("--error case----");
//			RequestDispatcher rd = req.getRequestDispatcher("error.jsp");
//			rd.forward(req, resp);
//		}
	}

}

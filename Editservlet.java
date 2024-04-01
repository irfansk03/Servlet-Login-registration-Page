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

public class Editservlet extends HttpServlet{
	
	public Editservlet() {
		System.out.println("inside the Editservlet");
	}
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	System.out.println("Inside the srevice Editservlet");
	
	String inputid=req.getParameter("id");
	
	System.out.println(inputid);
	
	try {
		
		Class.forName("com.mysql.jdbc.Driver");//// com.mysql.cj.jdbc.Driver
		Connection connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/student_db2","root","Pass@123");

		PreparedStatement ps = connection.prepareStatement("select * from studentdata where id =? ");

		ps.setString(1, inputid);
		
		ResultSet rs=ps.executeQuery();	  
		 if(rs.next()) {
			 
			 String id =rs.getString(1);
			  String fname =rs.getString(2);
			  String lname =rs.getString(3); 
			  String email =rs.getString(4);
			  
			  System.out.println(id + "--" + fname + "--" +lname +"--"+email);
			  
			  Student s1 = new Student();
			  s1.setId(id);
			  s1.setFname(fname);
			  s1.setLname(lname);
			  s1.setEmail(email);
			  req.setAttribute("STUDENT_DATA", s1);
			 
		 }
		RequestDispatcher rd=req.getRequestDispatcher("edit_servelet.jsp");
		rd.forward(req, resp);
		 
		
	}catch(Exception e) {
		System.out.println(e);
	}
}
}

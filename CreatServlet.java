package com.tech;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreatServlet extends HttpServlet{
	
	public CreatServlet() {
		System.out.println("inside CreatServlet constructor");
	}
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	String id = req.getParameter("sid");
	String fname = req.getParameter("sfname");
	String lname = req.getParameter("slname");
	String email = req.getParameter("semail");

	System.out.println(id + " -- " + fname + " -- " + lname + " -- " + email);

	try {
		Class.forName("com.mysql.jdbc.Driver");  
		Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/student_db2","root","Pass@123");
		PreparedStatement ps = con.prepareStatement("insert into student_db2.studentdata (id, fname, lname, email) values (?,?,?,?)");
		
		ps.setString(1, id);
		ps.setString(2, fname);
		ps.setString(3, lname);
		ps.setString(4, email);

		int records=ps.executeUpdate();
		System.out.println("records inserted -->" +records);
		//success case
		RequestDispatcher rd = req.getRequestDispatcher("create-success.jsp");
		rd.forward(req, resp);
		
	}catch (Exception ex) {
		System.out.println("inside exception case" + ex);
		// failure case
		RequestDispatcher rd = req.getRequestDispatcher("create-failure.jsp");
		rd.forward(req, resp);
	}

}
}

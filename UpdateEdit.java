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

public class UpdateEdit extends HttpServlet{
	
	public UpdateEdit() {
		System.out.println("Inside the UpdateEdit constructor");
	}
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	System.out.println("Inside the UpdateEdit service method");
	
	String sid=req.getParameter("sid");
	String sfname=req.getParameter("sfname");
	String slname=req.getParameter("slname");
	String semail=req.getParameter("semail");
	
	 System.out.println(sid + "--" + sfname + "--" + semail + "---" + semail);
	 
	 try {
		 
		 Class.forName("com.mysql.jdbc.Driver");//// com.mysql.cj.jdbc.Driver
			Connection connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/student_db2","root","Pass@123");

			PreparedStatement ps = connection.prepareStatement("UPDATE studentdata SET fname=?, lname=?, email=? WHERE id=?");
			
			ps.setString(1, sfname);
			ps.setString(2, slname);
			ps.setString(3, semail);
			ps.setString(4, sid);
			
			int recordUpdates=  ps.executeUpdate();
    		System.out.println("records udpate---" +recordUpdates);
    		
    		RequestDispatcher rd=req.getRequestDispatcher("/getALL");
    		rd.forward(req, resp);

		 
	 }catch(Exception e) {
		 System.out.println(e);
	 }
	
}
}

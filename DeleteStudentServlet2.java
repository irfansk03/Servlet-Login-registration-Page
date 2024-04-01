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

public class DeleteStudentServlet2 extends HttpServlet{

		public DeleteStudentServlet2() {
		System.out.println("inside DeleteStudentServlet cons");
		}
		
		@Override
		protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("inside service DeleteStudentServlet method");
		String id= req.getParameter("id");
		System.out.println("input id to be deleted -->" +id);
		
		//delete from student_db where id=?
		
		try {
			Class.forName("com.mysql.jdbc.Driver");//// com.mysql.cj.jdbc.Driver
			Connection connection=DriverManager.getConnection( "jdbc:mysql://localhost:3306/student_db2","root","Pass@123");

			PreparedStatement ps = connection.prepareStatement("delete  from studentdata where id =? ");

			ps.setString(1, id);
			int records=ps.executeUpdate();
			System.out.println("records ->" +records);
			
			// send to get ALl jsp with data 
			
			RequestDispatcher rd = req.getRequestDispatcher("/getALL");//servelt to servlet
			rd.forward(req, res);
			
		} catch (Exception e) {
			System.out.println("inside exception case" +e);
		}
		}
	}

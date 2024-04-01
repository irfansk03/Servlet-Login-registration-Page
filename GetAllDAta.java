package com.tech;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetAllDAta  extends HttpServlet{
	
	public GetAllDAta() {
		System.out.println("inside the get data ");
	}
@Override
protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
try {
	Class.forName("com.mysql.jdbc.Driver");  
	Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/student_db2","root","Pass@123");
	PreparedStatement ps = con.prepareStatement("select * from student_db2.studentdata");
	ResultSet rs = ps.executeQuery();
	
//	ArrayList<String> al = new ArrayList<>();
//	ArrayList<String> al2 = new ArrayList<>();
	
	ArrayList<Student>stdList = new ArrayList<>();
	
	
	
	
	while(rs.next()) {
		String id = rs.getString(1);
		String fname = rs.getString(2);
		String lname = rs.getString(3);
		String email = rs.getString(4);
		
		Student s1= new Student();
		s1.setId(id);
		s1.setFname(fname);
		s1.setLname(lname);
		s1.setEmail(email);
		
		stdList.add(s1);
		
		req.setAttribute("STD_LIST", stdList);
		
//		al.add(id);
//		
//		al2.add(email);
//		
//		req.setAttribute("sid", al);
//		req.setAttribute("snames", al2);
	}
	RequestDispatcher rd = req.getRequestDispatcher("GetAllData1.jsp");
	rd.forward(req, resp);
	
	
	
}catch (Exception ex) {
	System.out.println("inside exception case" + ex);
}
	
}
}

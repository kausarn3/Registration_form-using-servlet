

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FirstServlet1 extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static Connection conn;
	private static java.util.Date date;
    public FirstServlet1() {
        super();
         }

	public void init(ServletConfig config) throws ServletException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			try {
				conn=DriverManager.getConnection("jdbc:mysql://localhost/student","kausar","admin123");
			} catch (SQLException e) {
				System.out.println(e);
				
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			e.printStackTrace();
		}	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int sno=Integer.parseInt(request.getParameter("snumber"));
		String firstname=request.getParameter("First_Name");
		String lastname=request.getParameter("Last_Name");
		//String birthday=request.getParameter("Birthday");
		
		try {
			date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("Birthday"));
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		
		
		
		String emailid=request.getParameter("Email_Id");
		BigDecimal mobileno = new BigDecimal(request.getParameter("Mobile_Number"));
		String gender=request.getParameter("Gender");
		String city=request.getParameter("City");
		int pincode=Integer.parseInt(request.getParameter("Pin_Code"));
		String state=request.getParameter("State");
		try {
			PreparedStatement ps=conn.prepareStatement("insert into regform values(?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1,sno);
			ps.setString(2,firstname);
			ps.setString(3,lastname);
			ps.setDate(4,sqlDate);
			ps.setString(5,emailid);
			ps.setBigDecimal(6,mobileno);
			ps.setString(7,gender);
			ps.setString(8,city);
			ps.setInt(9,pincode);
			ps.setString(10,state);
			ps.executeUpdate();
			PrintWriter w=response.getWriter();
			w.print("Thanks For Registration");
			conn.close();
			
			
		} catch (SQLException e) {
		System.out.println(e);
			e.printStackTrace();
		}
		

	}

}

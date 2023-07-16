package shoppingApp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/LoginCheckDBAdmin")
public class LoginCheckDBAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	
    public LoginCheckDBAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}

	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        
		String uname = request.getParameter("uname");
		String pwd = request.getParameter("pwd");
		
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		ResultSet rs= null;
		ResultSet rs2 = null;
		try
		{
			ps = con.prepareStatement("select * from admins where adminname=? and password = ?");
			ps.setString(1, uname);
			ps.setString(2, pwd);
			rs = ps.executeQuery();   
			
			HttpSession session = request.getSession();
			ps2 = con.prepareStatement("INSERT INTO adminlog (adminname, loginTime,SessionID) VALUES (?, CONCAT(CURDATE(), ' ', CURTIME()),?)");
		    ps2.setString(1, uname);
		    ps2.setString(2, session.getId());
			
		    ps2.execute();
		    
		  
			if(rs.next())  
			{
				
				session.setAttribute("uname", rs.getString(2));
				session.setAttribute("pwd", rs.getString(3));
				
				session.setMaxInactiveInterval(0);
				
				Cookie [] allc = request.getCookies();
				
				if(allc != null)
				{
					for(Cookie c : allc)
					{
						if(c.getName().equals("loginerror"))
						{
							c.setMaxAge(0);
							response.addCookie(c);
						}							
					}
				}
				RequestDispatcher rd = request.getRequestDispatcher("/adminwel");
				rd.forward(request, response);
			}
			else
			{
				Cookie c = new Cookie("loginerror","Wrong_UID/PWD");
				response.addCookie(c);
				response.sendRedirect("/ShoppingApp/LoginAdmin.jsp");
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
	 }	
	
}


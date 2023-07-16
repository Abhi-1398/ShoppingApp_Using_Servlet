package shoppingApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */
@WebServlet("/logout")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	
	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		out.print("You have been Logged Out");
		HttpSession session = request.getSession(false);
		
		
		if(session != null)
		{
			PreparedStatement ps2 = null;
			ResultSet rs2 = null;
			try {
				String uname = (String) session.getAttribute("uname");
				ps2 = con.prepareStatement("UPDATE adminlog SET logoutTime = CONCAT(CURDATE(), ' ', CURTIME()) WHERE SessionID = ? ");
			    ps2.setString(1, session.getId());
				
			    ps2.execute();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			session.invalidate();
		}
		
		out.print("<br/>");
		out.print("<br/><a href=\"/ShoppingApp/LoginAdmin.jsp\">Revert Back to Login</a><br/>");
	}

}

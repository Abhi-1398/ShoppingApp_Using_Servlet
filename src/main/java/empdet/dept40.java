package empdet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dept40")
public class dept40 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Connection con;
	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {

        	 Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
        	 ResultSet rs = st.executeQuery("select * from emp where deptno =40");
        	 
        	 response.getWriter().print("<table border =1>");
        	 
        	 if(rs.next())
        	 {
        		 rs.absolute(0);
        		 while(rs.next())
        		 {
        		 response.getWriter().print("<tr>");
        		 
        		 	response.getWriter().print("<td>");
        		 
                  		response.getWriter().print(rs.getString(1));
                  
                  	response.getWriter().print("</td>");
        		 
                    response.getWriter().print("<td>");
        		 
                        response.getWriter().print(rs.getString(2));
                  
        		    response.getWriter().print("</td>");
        		 
        		 response.getWriter().print("</tr>");
        	 
        		 }
        	 }
        	 else
        	 {
        		 response.getWriter().print("No record fetched");
        	 }
        	 
        	 response.getWriter().print("</table>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 String url = "http://localhost:8080/StudentProject/StudentCourse.jsp";
         response.getWriter().print("<a href="+url+" >Click for Main Page</a>");
	}
}

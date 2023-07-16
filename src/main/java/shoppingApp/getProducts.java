package shoppingApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/getProducts")
public class getProducts extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   Connection con;
    public getProducts() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                         		
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                     
		
        int cid = Integer.parseInt(request.getParameter("cid")); 
		PrintWriter out =response.getWriter();
		
		PreparedStatement ps = null;
		ResultSet rs = null;
		try
		{
			
			ps = con.prepareStatement("Select * from product where cat_id = ?");
			ps.setInt(1, cid);
			rs = ps.executeQuery();
			 out.print("<table border=1>");
			 
			 out.print("<tr>");
			  	out.print("<td>");
			  	   out.print("Product ID");
				out.print("</td>");
				out.print("<td>");
					out.print("Product Name");
				out.print("</td>");
				out.print("<td>");
					out.print("Product Description");
				out.print("</td>");
				out.print("<td>");
					out.print("Product Price");
				out.print("</td>");
				out.print("<td>");
					out.print("Add to Cart");
				out.print("</td>");
			 out.print("</tr>"); 
			 
			      while(rs.next())
			      {
			    	  out.print("<tr>");
					  	out.print("<td>");
					  	   out.print(rs.getInt(1));
						out.print("</td>");
						out.print("<td>");
							out.print(rs.getString(2));
						out.print("</td>");
						out.print("<td>");
							out.print(rs.getString(3));
						out.print("</td>");
						out.print("<td>");
							out.print(rs.getInt(4));
						out.print("</td>");
						out.print("<td>");
							out.print("<a href='addtocart?pid="+rs.getInt(1)+"'>add to cart</a>");
						out.print("</td>");
					 out.print("</tr>");
			    	  
			      }
			 
		     out.print("</table>");
		     
		     String url = "http://localhost:8080/ShoppingApp/home";
		     out.print("<a href="+url+">Go To Home Page</a>");
		     
		     out.print("<br/>");
		     
		     String urllogout = "http://localhost:8080/ShoppingApp/Login.jsp";
		     out.print("<a href="+urllogout+">LogOut</a>");
			
		}
		catch(Exception e)
		{
			
		}
		finally
		{
			try
			{
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}

}

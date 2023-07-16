package shoppingApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/viewcart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Connection con;
    public ViewCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
	
		List<Integer> pidint = (List<Integer>)session.getAttribute("cart");
		
		out.print("<br/>There are "+ pidint.size()+" item(s) in the cart<br/><br/>");
		
		
		
		
	     List<Cart> list = new ArrayList<>();
		 list = (List<Cart>)session.getAttribute("selectedproducts");
	   
		 out.print("Below Are the Products You have selected<br/>");
	     
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
			out.print("Delete from Cart");
			out.print("</td>");
			out.print("</tr>"); 
		 
		 for( Cart s : list )
	     {
	    	 //out.print(s+"<br/>");
	    	 out.print("<tr>");
				
			  	out.print("<td>");
			  	   out.print(s.pid);
				out.print("</td>");
				
				out.print("<td>");
					out.print(s.pname);
				out.print("</td>");
				
				out.print("<td>");
					out.print(s.pdesc);
				out.print("</td>");
				
				out.print("<td>");
					out.print(s.pprice);
				out.print("</td>");
				out.print("<td>");
				out.print("<a href='deleteitem?pid="+s.pid+"'>Delete</a>");
			    out.print("</td>");
			 	out.print("</tr>"); 
			
	     }
		 out.print("</table><br/>");
	     out.print("<a href=\"/ShoppingApp/home\">go to home</a>");
	}
}

package shoppingApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addtocart")
public class addtocart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  Connection con;
    public addtocart() {
        super();
        
    }
    public void init(ServletConfig config) throws ServletException {
		con = (Connection)config.getServletContext().getAttribute("jdbccon");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int pid = Integer.parseInt(request.getParameter("pid"));
		PrintWriter out =response.getWriter();
		
		PreparedStatement ps =null;
		ResultSet rs = null;
		int p_id=0;
		String pname="";
		String pdesc="";
		int pprice =0;
		int catid=0;
		try {
		    ps = con.prepareStatement("Select * from product where p_id=?");
		    ps.setInt(1, pid);
		    
		    HttpSession session = request.getSession();
		    rs = ps.executeQuery();
		    List<Integer> list = (List<Integer>)session.getAttribute("cart");
		    if(list == null)  //only for first selection
			{
		    	list = new ArrayList<>();  //empty
			}
		    
		    list.add(pid);
			session.setAttribute("cart", list);
			
			
			out.print("<br/>There are "+ list.size()+" item(s) in the cart<br/><br/>");
			
			while(rs.next())
			{
				p_id=rs.getInt(1);
				pname=rs.getString(2);
			    pdesc=rs.getString(3);
				pprice = rs.getInt(4);
				catid=rs.getInt(5);
			}
			out.print("You have selected Below Product");
			
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
			
			out.print("</tr>"); 
		 
			out.print("<tr>");
			
		  	out.print("<td>");
		  	   out.print(p_id);
			out.print("</td>");
			
			out.print("<td>");
				out.print(pname);
			out.print("</td>");
			
			out.print("<td>");
				out.print(pdesc);
			out.print("</td>");
			
			out.print("<td>");
				out.print(pprice);
			out.print("</td>");
			
			out.print("</tr>"); 
		out.print("</table>");
		
		
		//-----------------------------
		
		List<Cart> selprolist = (List<Cart>)session.getAttribute("selectedproducts");
	    if(selprolist == null)  //only for first selection
		{
	    	selprolist = new ArrayList<>();  //empty
		}
	    Cart cart = new Cart(p_id,pname,pdesc,pprice,catid);
	    selprolist.add(cart);
		session.setAttribute("selectedproducts", selprolist);
		//-----------------------------
		out.print("<br/>");
			
	out.print("<br/>");
	
	out.print("<a href=\"/ShoppingApp/viewcart\">View Final Cart</a><br/>");
	
	String url = "http://localhost:8080/ShoppingApp/home";
    out.print("<a href="+url+">Go To Home Page</a>");
    
    out.print("<br/>");
    
    String urllogout = "http://localhost:8080/ShoppingApp/Login.jsp";
    out.print("<a href="+urllogout+">LogOut</a>");
		
		} 
		catch(Exception e){
			e.printStackTrace();
		}finally {

		}
	}

}

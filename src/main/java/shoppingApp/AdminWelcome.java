package shoppingApp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/adminwel")
public class AdminWelcome extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public AdminWelcome() {
        super();
      
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		out.print("<h2>Welcome Admin "+session.getAttribute("uname")+" </h2>");
		out.print("below is your session id : "+session.getId());
		
		out.print("<br/>");
		out.print("<a href=\"/ShoppingApp/addcategory\">Add Category</a> <br/>");
		out.print("<a href=\"/ShoppingApp/addproduct\">Add Products</a><br/>");
		
		out.print("<br/><a href=\"/ShoppingApp/logout\">Logout</a><br/>");
	}

}

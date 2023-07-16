package shoppingApp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/deleteitem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteItem() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		
		
		PrintWriter out = response.getWriter();
	     List<Cart> list  = (List<Cart>)session.getAttribute("selectedproducts");
		 int index=-1;
		 for(Cart s: list) {
			 if(s.pid==Integer.parseInt(request.getParameter("pid")))
			 {
				 index= list.indexOf(s);
				 
			 }
		 }
		 list.remove(index);
		 
		 session.setAttribute("selectedproducts", list);
		 
		 List<Integer> pidlist = (List<Integer>)session.getAttribute("cart");
		 int index2 =-1;
		 for(Integer c: pidlist){
			 if(c == Integer.parseInt(request.getParameter("pid"))) {
				 
			     index2=pidlist.indexOf(c);
			 }
		 }
		 pidlist.remove(index2);
		 
		 session.setAttribute("cart", pidlist);
		 response.sendRedirect("/ShoppingApp/viewcart");
	}

}

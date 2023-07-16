package empdet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class EmpListner implements ServletContextListener {

	Connection con;

    public EmpListner() {

    }

    public void contextDestroyed(ServletContextEvent e)  { 
    	try {
			con.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }

    public void contextInitialized(ServletContextEvent e)  { 

    	String driver = e.getServletContext().getInitParameter("diverclass");
    	String url = e.getServletContext().getInitParameter("jdbcurl");		
    	String user = e.getServletContext().getInitParameter("user");
    	String pwd = e.getServletContext().getInitParameter("password");
    	
    	try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user , pwd);
	        e.getServletContext().setAttribute("jdbccon", con);
	        System.out.println("Connected");
			
		} catch (ClassNotFoundException|SQLException e1) {
			e1.printStackTrace();
		}
    	
    	
    }
	
}

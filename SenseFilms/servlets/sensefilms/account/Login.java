package sensefilms.account;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sensefilms.entities.User;
import sensefilms.exceptions.LoggedException;
import sensefilms.interfaces.ILoginLogic;
import sensefilms.logic.LoginLogic;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ILoginLogic _loginLogic;

    /**
     * Default constructor. 
     */
    public Login() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		_loginLogic = new LoginLogic();
			// Retrieve data from UI
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User currentUser = new User();
		currentUser.setPassword(password);
		currentUser.setUsername(username);
		try 
		{
			if(_loginLogic.isValidLogin(currentUser)) 
			{
				response.sendRedirect("");
			}
			else
			{
				//Show error
			}
		}
		catch(LoggedException loggex) 
		{
			response.sendRedirect("");
		}
	}

}
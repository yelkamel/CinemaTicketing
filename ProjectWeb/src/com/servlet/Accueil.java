package com.servlet;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sessionBeans.*;
import EntityBeans.*;

/**
 * Servlet implementation class Traitement
 */
public class Accueil extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersFacadeLocal userFacade;
	
	@EJB
	private ReservationsFacadeLocal reservationFacade;
	
    /**
     * Default constructor. 
     */
    public Accueil()
    {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String login = (String)request.getParameter("login");
		String password = (String)request.getParameter("password");
		String message;
		
		HttpSession session = request.getSession();
		Users user = new Users();
		List<Users> users = userFacade.findbyLogin(login);  
		
		//utilisateur existant
		if (users.size() > 0)
		{	
			user = users.get(0);
			
			//identifiants et mot de passe valide
			if (login.equals(user.getLogin()) && password.equals(user.getPassword()))
			{
				// pas administrateur
				if (user.getIsAdmin().equals("no"))
				{
					List<Reservations>lreservations = reservationFacade.findbyUser(user, "no");
					request.getSession().setAttribute("ListReservations", lreservations);
					//création session pour l'utilisateur
					session.setAttribute("login", user.getLogin());
					session.setAttribute("prenom", user.getFirstname());
					session.setAttribute("user", user);
				
					//redirection page client
					this.getServletContext().getRequestDispatcher("/accueil.jsp").forward(request, response);
				}
				// administrateur
				else
				{
					//création session pour l'administrateur
					session.setAttribute("login", user.getLogin());
					session.setAttribute("prenom", user.getFirstname());
					
					//redirection page administrateur
					this.getServletContext().getRequestDispatcher("/Gestion_Films").forward(request, response);
				}
			}
			else
			{
				message = "Veuillez utiliser des identifiants valides";
				request.setAttribute("message", message);
				this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
		}
		else
		{
			message = "Veuillez utiliser des identifiants valides";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	}
}
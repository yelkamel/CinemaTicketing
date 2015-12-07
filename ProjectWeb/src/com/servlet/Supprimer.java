package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityBeans.Commandes;
import EntityBeans.Reservations;
import EntityBeans.Seances;
import EntityBeans.Users;

import sessionBeans.CommandesFacadeLocal;
import sessionBeans.ReservationsFacadeLocal;
import sessionBeans.SeancesFacadeLocal;
import sessionBeans.UsersFacadeLocal;

/**
 * Servlet implementation class Supprimer
 */
public class Supprimer extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private ReservationsFacadeLocal reservationFacade;
	
	@EJB
	private SeancesFacadeLocal seanceFacade;
	
	@EJB
	private UsersFacadeLocal userFacade;
	
	@EJB
	private CommandesFacadeLocal commandeFacade;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Supprimer()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String login = (String)request.getSession().getAttribute("login");
		
		//utilsateur connecté
		if (login != null)
		{
			List<Users> users = userFacade.findbyLogin(login);  
			Users user = users.get(0);
			
			//on récupère la réservation
			Reservations res = reservationFacade.find(Integer.parseInt(request.getParameter("id")));
			//réservation existe
			if (res != null)
			{
				//mise à jour du nombre de place de la séance et de réservations
				Seances seance = seanceFacade.find(res.getSeance().getId());
				seance.setBooking(seance.getBooking() - res.getQuantite());
	        	seance.setPlaces(seance.getPlaces() + res.getQuantite());
	        	seanceFacade.update(seance);
	        	
	        	//suppression de la commande
	        	/*if (commandeFacade.getCommandes(res).size() > 0)
	        	{
	        		Commandes commande = commandeFacade.getCommandes(res).get(0);
	        		commandeFacade.remove(commande);
	        	}*/
	        	
	        	//suppresion de la réservation
				reservationFacade.remove(res);
				
				//on récupère la nouvelle liste de réservation
				List<Reservations>lreservations = reservationFacade.findbyUser(user, "no");
				request.getSession().setAttribute("ListReservations", lreservations);
				
				this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
			}
		}
		else
		{
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		// TODO Auto-generated method stub
	}

}

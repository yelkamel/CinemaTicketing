package com.servlet;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Reservations;
import EntityBeans.Seances;
import EntityBeans.Users;

import sessionBeans.CinemasFacadeLocal;
import sessionBeans.FilmsFacadeLocal;
import sessionBeans.ReservationsFacadeLocal;
import sessionBeans.SeancesFacadeLocal;
import sessionBeans.UsersFacadeLocal;

/**
 * Servlet implementation class Reservation
 */
public class Panier extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FilmsFacadeLocal filmFacade;
	
	@EJB
	private SeancesFacadeLocal seanceFacade;
	
	@EJB
	private CinemasFacadeLocal cinemaFacade;
	
	@EJB
	private ReservationsFacadeLocal reservationFacade;
	
	@EJB
	private UsersFacadeLocal userFacade;
	
	List<Films>lfilms = new ArrayList<Films>();
	List<Cinemas>lcinemas = new ArrayList<Cinemas>();
	List<Seances>lseances = new ArrayList<Seances>();
	List<String>lhoraires = new ArrayList<String>();
	List<String>ldates = new ArrayList<String>();
	List<Reservations>lreservations = new ArrayList<Reservations>();
	
	//List<Reservations> reservations = new ArrayList<Reservations>();
	Reservations reservation = new Reservations();
	String id = new String();
	Random rand = new Random();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Panier()
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
		if (login != null)
		{
			List<Users> users = userFacade.findbyLogin(login);  
			Users user = users.get(0);
			lreservations = (List<Reservations>)request.getSession().getAttribute("ListReservations");
			request.getSession().setAttribute("ListReservations", lreservations);
			this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
		}
		else
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String login = (String)request.getSession().getAttribute("login");
		Reservations res = new Reservations();
		
		//utilisateur connecté
		if (login != null)
		{
			String id_film = request.getParameter("id_film");
			String id_cine = request.getParameter("id_cine");
			String id_seance = request.getParameter("id_seance");
			String horaire = request.getParameter("hour");
			String date = request.getParameter("date");
			String quantite = request.getParameter("quantite");
			
			List<Users> users = userFacade.findbyLogin(login);  
			Users user = users.get(0);
			//List<Reservations>reserv = reservationFacade.findbyUser(user);
			
			if (id_film != null && id_cine != null && id_seance != null && horaire != null && date != null && quantite != null)
			{
				Films film = filmFacade.find(Integer.parseInt(id_film));
				Cinemas cinema = cinemaFacade.find(Integer.parseInt(id_cine));
				Seances seance = seanceFacade.find(Integer.parseInt(id_seance));
				
				/*String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		        for (int i=0; i<5; i++)
		        {
		        	id = id.concat(Character.toString(alphabet.charAt(rand.nextInt(alphabet.length()))));
		        }*/
		        
				//id de réservation
		        id = ((String)request.getSession().getAttribute("login")).concat(Character.toString('-')).concat(id_seance).concat(Character.toString('-')).concat(date).concat(Character.toString('-')).concat(horaire).concat(Character.toString('-')).concat(id_film).concat(Character.toString('-')).concat(id_cine);
		        //reservation = reservationFacade.find("test");
		        
		        //reservation non trouvée
		        
		        	BigDecimal i = new BigDecimal(quantite);
		        	BigDecimal j = new BigDecimal(String.valueOf(seance.getPrice()));
		        	BigDecimal price = i.multiply(j);
		        	res.setFilm(film);
		        	res.setSeance(seance);
		        	res.setCinema(cinema);
		        	res.setDate(date);
		        	res.setHoraire(horaire);
		        	res.setQuantite(Integer.parseInt(quantite));
		        	res.setPrice(price.doubleValue());
		        	res.setClient(user);
		        	res.setRecord("no");
		        	reservationFacade.create(res);
		        	//lreservations.add(res);
		        
		        //réservation existante déjà
		   /*     else
		        {
		        	BigDecimal i = new BigDecimal(String.valueOf(reservation.getQuantite() + Integer.parseInt(quantite)));
		        	BigDecimal j = new BigDecimal(String.valueOf(seance.getPrice()));
		        	BigDecimal price = i.multiply(j);
		        	reservation.setQuantite(reservation.getQuantite() + Integer.parseInt(quantite));
		        	reservation.setPrice(price.doubleValue());
		        	reservationFacade.update(reservation);
		        }
		     */   
		        //mise à jour des places et réservations de séance
		    	seance.setBooking(seance.getBooking() + Integer.parseInt(quantite));
	        	seance.setPlaces(seance.getPlaces() - Integer.parseInt(quantite));
	        	seanceFacade.update(seance);
		        
		        lreservations = reservationFacade.findbyUser(user,"no");
		        lfilms.add(film);
				lcinemas.add(cinema);
				lseances.add(seance);
				lhoraires.add(horaire);
				ldates.add(date);
		        
		        /*for (Reservations r : reservations)
		        {
		        	if (!(lreservations.contains(r)) && ((String)request.getSession().getAttribute("login")).equals(r.getClient()))
		        		lreservations.add(r);
		        }*/
				
				/*request.getSession().setAttribute("Film", film);
				request.getSession().setAttribute("ListFilms", lfilms);
				request.getSession().setAttribute("Cinema", cinema);
				request.getSession().setAttribute("ListCinemas", lcinemas);
				request.getSession().setAttribute("Seance", seance);
				request.getSession().setAttribute("ListSeances", lseances);
				request.getSession().setAttribute("Horaire", horaire);			
				request.getSession().setAttribute("ListHoraires", lhoraires);
				request.getSession().setAttribute("Date", date);			
				request.getSession().setAttribute("ListDates", ldates);*/
				request.getSession().setAttribute("ListReservations", lreservations);
				
				request.setAttribute("id_film", id_film);
				request.setAttribute("id_cine", id_cine);
				request.setAttribute("id_seance", id_seance);
				request.setAttribute("horaire", horaire);
				request.setAttribute("date", date);
				
				this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
			}
			else
			{
				this.getServletContext().getRequestDispatcher("/panier.jsp").forward(request, response);
			}
		}
		else
		{
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
	}

}

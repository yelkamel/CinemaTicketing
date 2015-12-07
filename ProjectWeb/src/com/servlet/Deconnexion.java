package com.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Seances;

/**
 * Servlet implementation class Deconnection
 */
public class Deconnexion extends HttpServlet
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deconnexion()
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession session = request.getSession();
		String message = new String();
		
		// connexion existante
		if (session != null)
		{
			//suppresion des paramètres de session
			
			List<Films>lfilms = (List<Films>)session.getAttribute("ListFilms");
			List<Cinemas>lcinemas = (List<Cinemas>)session.getAttribute("ListCinemas");
			List<Seances>lseances = (List<Seances>)session.getAttribute("ListSeances");
			List<String>lhoraires = (List<String>)session.getAttribute("ListHoraires");
						
			if (lfilms != null)
				lfilms.clear();
			if (lcinemas != null)
				lcinemas.clear();
			if (lseances != null)
				lseances.clear();
			if (lhoraires != null)
				lhoraires.clear();
			
			session.removeAttribute("login");
			session.removeAttribute("admin");
			session.removeAttribute("ListFilms");
			session.removeAttribute("ListCinemas");
			session.removeAttribute("ListSeances");
			session.invalidate();
			message = "Déconnexion réussie!";
			request.setAttribute("message", message);
			
			//redirection page d'accueil 
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
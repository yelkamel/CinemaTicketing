package com.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import javax.ejb.EJB;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sessionBeans.*;
import EntityBeans.*;
import Mail.SendMail;

/**
 * Servlet implementation class Traitement
 */
public class Oublie extends HttpServlet
{
	private static final long serialVersionUID = 1L;
	
	@EJB
	private UsersFacadeLocal userFacade;
    /**
     * Default constructor. 
     */
    public Oublie()
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
		String login = request.getParameter("login");
		String password = new String();
		String message;
	
		Users user = new Users();
		List<Users> users = userFacade.findbyLogin(login);  
		
		//utilisateur existant
		if (users.size() > 0)
		{	
			user = users.get(0);

			//regénération du mot de passe
			String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890_-@#&'(!?)$%?:;/.?,";
	        Random rand = new Random();
	        for (int i=0; i<10; i++)
	        {
	        	password = password.concat(Character.toString(alphabet.charAt(rand.nextInt(alphabet.length()))));
	        }
	        //mise à jour du mot de passge
	        user.setPassword(password);
	        userFacade.update(user);
	        
			//envoie de l'email de regénération de mot de passe
			String corps = new String();
			corps = "Bonjour " + user.getFirstname() + " " + user.getName() + "," + System.getProperty("line.separator") + System.getProperty("line.separator");
			corps += "Votre demande de regénération de mot de passe a bien été prise en compte. Voici vos nouveaux identifiants pour vous connecter:" + System.getProperty("line.separator");
			corps += "Login: " + user.getLogin() + System.getProperty("line.separator");
			corps += "Mot de passe: " + password + System.getProperty("line.separator") + System.getProperty("line.separator");
			corps += "A bientôt!" + System.getProperty("line.separator") + System.getProperty("line.separator");
			corps += "--" + System.getProperty("line.separator");
			corps += "Ce message a été généré automatiquement. Il est inutile d'y répondre";
				
			SendMail mail = new SendMail();
			try
			{
				mail.sendMail("no-reply@gmail.com", user.getEmail(), " Cinema J2EE - Regénération de mot de passe", corps);
			}
			catch (AddressException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			catch (MessagingException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
			message="Votre nouveau mot de passe a été envoyé votre boite email." + System.getProperty("line.separator") + " Veuillez consultez vos mails";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
		//utilisateur inexistant
		else
		{
			message = "Veuillez entrer un login valide";
			request.setAttribute("message", message);
			this.getServletContext().getRequestDispatcher("/oublie.jsp").forward(request, response);
		}
	}
}
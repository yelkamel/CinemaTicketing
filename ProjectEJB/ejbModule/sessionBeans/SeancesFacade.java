package sessionBeans;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import EntityBeans.Cinemas;
import EntityBeans.Films;
import EntityBeans.Seances;

/**
 * Session Bean implementation class SessionFacade
 */
@Stateless
public class SeancesFacade implements SeancesFacadeLocal
{
	@PersistenceContext(unitName="Cinema-ejbPU")
	private EntityManager entityMgr;
	
	@Override
	public void create(Seances seance)
	{
		entityMgr.persist(seance);		
	}

	@Override
	public void update(Seances seance)
	{
		entityMgr.merge(seance);		
	}

	@Override
	public void remove(Seances seance)
	{
		entityMgr.remove(entityMgr.merge(seance));
	}

	@Override
	public Seances find(Object id)
	{
		return entityMgr.find(Seances.class, id);
	}

	/*
	 * On récupère la liste des séances en fonction du film passé en paramètre
	 */
	@Override
	public List<Seances> getSeances(Films film)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances WHERE seances.film = :film";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("film", film);
		return (List<Seances>)requete.getResultList();
	}

	public List<Seances> getSeancesbyCinema(Cinemas cinema)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances WHERE seances.cinema = :cinema";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("cinema", cinema);
		return (List<Seances>)requete.getResultList();
	}
	
	public List<Seances> getSeancesbyFilm(Films film)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances WHERE seances.film = :film";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("film", film);
		return (List<Seances>)requete.getResultList();
	}
	
	public List<Seances> getSeancesbyDate(Date date)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances WHERE seances.date = :date";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("date", date);
		return (List<Seances>)requete.getResultList();
	}
	
	public List<Seances> getSeancesbyTime(Time time)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances WHERE seances.horaire = :time";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("time", time);
		return (List<Seances>)requete.getResultList();
	}
	
	public List<Seances> getSeancesbyMultiCriteres(Cinemas cinema, Films film, Date date, Time heure)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances";
		Query requete = null;
		
		if (cinema == null && film == null && date == null && heure == null)
		{
			requete = entityMgr.createQuery(texteRequete);
		}
		
		if (cinema != null && film != null && date != null && heure != null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.film = :film AND seances.date = :date AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("film", film);
			requete = requete.setParameter("date", date);
			requete = requete.setParameter("heure", heure);
		}
		
		if (cinema != null && film == null && date == null && heure == null)
		{
			texteRequete += " WHERE seances.cinema = :cinema";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
		}
		
		if (cinema != null && film != null && date == null && heure == null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.film = :film";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("film", film);
		}
		
		if (cinema != null && film == null && date != null && heure == null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.date = :date";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("date", date);
		}
		
		if (cinema != null && film == null && date == null && heure != null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("heure", heure);
		}
		
		if (cinema != null && film != null && date != null && heure == null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.film = :film AND seances.date = :date";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("film", film);
			requete = requete.setParameter("date", date);
		}
		
		if (cinema != null && film != null && date == null && heure != null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.film = :film AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("film", film);
			requete = requete.setParameter("heure", heure);
		}
		
		if (cinema != null && film == null && date != null && heure != null)
		{
			texteRequete += " WHERE seances.cinema = :cinema AND seances.date = :date AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("cinema", cinema);
			requete = requete.setParameter("date", date);
			requete = requete.setParameter("heure", heure);
		}
		
		if (film != null && cinema == null && date == null && heure == null)
		{
			texteRequete += " WHERE seances.film = :film";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("film", film);
		}
		
		if (film != null && cinema == null && date != null && heure == null)
		{
			texteRequete += " WHERE seances.film = :film AND seances.date = :date";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("film", film);
			requete = requete.setParameter("date", date);
		}
		
		if (film != null && cinema == null && date == null && heure != null)
		{
			texteRequete += " WHERE seances.film = :film AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("film", film);
			requete = requete.setParameter("heure", heure);
		}
		
		if (film != null && cinema == null && date != null && heure == null)
		{
			texteRequete += " WHERE seances.film = :film AND seances.date = :date AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("film", film);
			requete = requete.setParameter("date", date);
			requete = requete.setParameter("heure", heure);
		}
		
		if (date != null && cinema == null && film == null && heure == null)
		{
			texteRequete += " WHERE seances.date = :date";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("date", date);
		}
		
		if (date != null && cinema == null && film == null && heure != null)
		{
			texteRequete += " WHERE seances.date = :date AND seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("date", date);
			requete = requete.setParameter("heure", heure);
		}
		
		if (heure != null && cinema == null && film == null && date == null)
		{
			texteRequete += " WHERE seances.horaire = :heure";
			requete = entityMgr.createQuery(texteRequete);
			requete = requete.setParameter("heure", heure);
		}
				
		return (List<Seances>)requete.getResultList();
	}
	
	public List<Seances> getSeancesbyAllCriteres(Films film, Cinemas cinema, Date date, Time horaire)
	{
		String texteRequete = "SELECT seances FROM Seances AS seances WHERE seances.film = :film AND seances.cinema = :cinema AND seances.date = :date AND seances.horaire = :horaire";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("film", film);
		requete = requete.setParameter("cinema", cinema);
		requete = requete.setParameter("date", date);
		requete = requete.setParameter("horaire", horaire);
		return (List<Seances>)requete.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Seances> findAll()
	{
		return (List<Seances>)entityMgr.createQuery("SELECT object(o) FROM Seances as o").getResultList();
	}
}

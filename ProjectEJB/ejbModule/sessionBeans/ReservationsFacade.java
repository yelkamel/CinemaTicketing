package sessionBeans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import EntityBeans.Films;
import EntityBeans.Reservations;
import EntityBeans.Seances;
import EntityBeans.Users;

/**
 * Session Bean implementation class Reservations
 */
@Stateless
public class ReservationsFacade implements ReservationsFacadeLocal
{

	@PersistenceContext(unitName="Cinema-ejbPU")
	private EntityManager entityMgr;

	@Override
	public void create(Reservations reservation)
	{
		entityMgr.persist(reservation);
	}

	@Override
	public void update(Reservations reservation)
	{
		entityMgr.merge(reservation);
	}

	@Override
	public void remove(Reservations reservation)
	{
		entityMgr.remove(entityMgr.merge(reservation));
		
	}

	@Override
	public Reservations find(Object id)
	{
		return entityMgr.find(Reservations.class, id);
	}

	/*
	 * On récupère la liste des réservations en fonction de l'utilisateur
	 */	
	@SuppressWarnings("unchecked")
	public List<Reservations> findbyUser(Users usr, String record)
	{
		String texteRequete = "SELECT reservations FROM Reservations AS reservations WHERE reservations.client = :usr AND reservations.record = :record";
		Query requete = entityMgr.createQuery(texteRequete);
		requete = requete.setParameter("usr", usr);
		requete = requete.setParameter("record", record);
		return (List<Reservations>)requete.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Reservations> findAll()
	{
		return (List<Reservations>)entityMgr.createQuery("SELECT object(o) FROM Reservations as o").getResultList();
	}

}

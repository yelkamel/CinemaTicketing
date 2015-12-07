package sessionBeans;

import java.util.List;

/*
 * Facade Locale pour les réservations de séances
 */

import javax.ejb.Local;

import EntityBeans.Reservations;
import EntityBeans.Users;

@Local
public interface ReservationsFacadeLocal
{
	void create(Reservations reservation);
	void update(Reservations reservation);
	void remove(Reservations reservation);

	Reservations find(Object id);
	List<Reservations> findbyUser(Users usr, String record);
	List<Reservations> findAll();
}

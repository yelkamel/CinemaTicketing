package sessionBeans;

import java.util.List;

import javax.ejb.Local;

import EntityBeans.Cinemas;
import EntityBeans.Users;

/*
 * Facade Locale pour les cinemas
 */

@Local
public interface CinemasFacadeLocal
{
	void create(Cinemas cinema);
	void update(Cinemas cinema);
	void remove(Cinemas cinema);

	Cinemas find(Object id);
	List<Cinemas> findbyName(String name);
	List<Cinemas> findAll();
}

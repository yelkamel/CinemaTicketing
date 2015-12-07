package sessionBeans;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/*
 * Facade Locale pour les films
 */

import javax.ejb.Local;

import EntityBeans.Cinemas;
import EntityBeans.Films;

@Local
public interface FilmsFacadeLocal
{
	void create(Films film);
	void update(Films film);
	void remove(Films film);

	Films find(Object id);
	List<Films> getFilms(String title);
	List<Films> findbyType(String type);
	List<Films>getFilmsbyMultiCriteres(String genre, Date sortie, Time duree);
	List<Films> findAll();
}

package EntityBeans;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/*
 * Bean de la table SEANCES
 */

@Entity
@Table(name = "CINEMA.SEANCES")
public class Seances implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;

	@Column(name = "DATE")
	private Date date;
	
	@Column(name = "HORAIRE")
	private Time horaire;
	
	@Column(name = "PRICE")
	private double price;
	
	@Column(name = "PLACES")
	private int places;
	
	@Column(name = "BOOKING")
	private int booking;

	@JoinColumn(name = "ID_FILM", referencedColumnName = "ID")
	@ManyToOne
	private Films film;
	
	@JoinColumn(name = "ID_CINEMA", referencedColumnName = "ID")
	@ManyToOne
	private Cinemas cinema;
	
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public Date getDate()
	{
		return date;
	}

	public void setDate(Date date)
	{
		this.date = date;
	}

	public Time getHoraire()
	{
		return horaire;
	}

	public void setHoraire(Time horaire)
	{
		this.horaire = horaire;
	}

	public double getPrice()
	{
		return price;
	}

	public void setPrice(double price)
	{
		this.price = price;
	}

	public int getPlaces()
	{
		return places;
	}

	public void setPlaces(int places)
	{
		this.places = places;
	}

	public int getBooking()
	{
		return booking;
	}

	public void setBooking(int booking)
	{
		this.booking = booking;
	}

	public Films getFilm()
	{
		return film;
	}

	public void setFilm(Films film)
	{
		this.film = film;
	}

	public Cinemas getCinema()
	{
		return cinema;
	}

	public void setCinema(Cinemas cinema)
	{
		this.cinema = cinema;
	}
}
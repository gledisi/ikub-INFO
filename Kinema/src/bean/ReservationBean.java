package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import dao.ReservationDao;
import entitete.Reservation;
import utility.Messages;

@ManagedBean(name="reservationBean")
@ViewScoped
public class ReservationBean {
	
	private Reservation reservation ;
	private List<Reservation> reservations;
	private ReservationDao reservationDao;
	
	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean ;
	@ManagedProperty(value = "#{movieBean}")
	private MovieBean movieBean ;
	@ManagedProperty(value = "#{showBean}")
	private ShowBean showBean ;

	@PostConstruct
	public void init() {
		this.reservationDao = ReservationDao.INSTANCE;
		refreshBean();
	}

	public void refreshBean() {

		this.reservation = new Reservation();
		this.reservations = reservationDao.getUsersReservation(userBean.getUser().getId());

	}
	
	public String addReservation() {
		System.out.println("duke u shtuar...");
		Reservation addReservation = new Reservation();
		addReservation.setPrice(23);
		addReservation.setDate(reservation.getDate());
		addReservation.setShow(showBean.getShow());
		addReservation.setUser(userBean.getUser());
		System.out.println("duke u shtuar...");
		if(reservationDao.addReservation(addReservation)) {
			Messages.addMessage("Rezervimi u krye!");
			System.out.println("u shtua.");
		}else {
			Messages.addMessage("Rezervimi nuk krye!");
			System.out.println("nuk u shtua.");
		}
		
		return null;
	}
	
	public String deleteReservation(int idRes) {
		System.out.println("duke u shtuar...");
		
		System.out.println("duke u shtuar...");
		if(reservationDao.deleteReservation(idRes)) {
			Messages.addMessage("Rezervimi u Fshi!");
			System.out.println("u shtua.");
		}else {
			Messages.addMessage("Rezervimi nuk Fshi!");
			System.out.println("nuk u shtua.");
		}
		
		return null;
	}
	

	public Reservation getReservation() {
		return reservation;
	}

	public void setReservation(Reservation reservation) {
		this.reservation = reservation;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public ReservationDao getReservationDao() {
		return reservationDao;
	}

	public void setReservationDao(ReservationDao reservationDao) {
		this.reservationDao = reservationDao;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public MovieBean getMovieBean() {
		return movieBean;
	}

	public void setMovieBean(MovieBean movieBean) {
		this.movieBean = movieBean;
	}

	public ShowBean getShowBean() {
		return showBean;
	}

	public void setShowBean(ShowBean showBean) {
		this.showBean = showBean;
	}
	
}

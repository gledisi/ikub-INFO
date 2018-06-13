
package bean;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import dao.ReservationDao;
import dao.ShowDao;
import entitete.Monitor;
import entitete.Movie;
import entitete.Reservation;
import entitete.Show;
import utility.Messages;

@ManagedBean
@ViewScoped
public class ShowBean {

	private Show show;
	private ShowDao showDao;
	private ReservationDao reservationDao;
	private List<Show> shows;
	private Date dateTime;
	@ManagedProperty(value = "#{movieBean}")
	private MovieBean movieBean;

	@ManagedProperty(value = "#{monitorBean}")
	private MonitorBean monitorBean;

	@PostConstruct
	public void init() {
		this.showDao = new ShowDao();
		this.reservationDao = ReservationDao.INSTANCE;
		refreshBean();
	}

	public void refreshBean() {

		this.show = new Show();
		this.shows = showDao.getAllShows();

	}

	public String addShow() throws ParseException {

		Show showAdd = new Show();
		Monitor monitor = new Monitor();
		Movie movie = new Movie();
		monitor.setId(monitorBean.getMonitor().getId());
		movie.setId(movieBean.getMovie().getId());
//		showAdd.setMovie(movieBean.getMovie());
//		showAdd.setMonitori(monitorBean.getMonitor());
		System.out.println("id e zgjedhur"+monitorBean.getMonitor().getId());
		showAdd.setMovie(movie);
		showAdd.setMonitori(monitor);
		showAdd.setDate(dateTime);
		showAdd.setTime(dateTime);

		if (canAddShow(showAdd)) {

			if (showDao.add(showAdd)) {
				Messages.addMessage("Shfaqja u shtua!");
			} else {
				Messages.addMessage("Shfaqja nuk u shtua!");
			}

		} else {
			Messages.addMessage("Shfaqja nuk mund te shtohet!\nPo transmetohet shfaqje ne kete orar!");
		}
		return null;
	}

	public String deleteShow(int idShow) {
		if (canDeleteShow(idShow)) {
			if (showDao.delete(idShow)) {
				Messages.addMessage("Shfaqja u fshi!");
			} else {
				Messages.addMessage("Shfaqja nuk u fshi!");
			}
		} else {
			Messages.addMessage("Shfaqja nuk mund te fshihet!\n Ka rezervime per kete shfaqje!!");
		}
		
		return null;
	}

	private boolean canAddShow(Show show) {
		List<Show> shows = showDao.getMonitorsShowByTime(show.getDate(), show.getMovie().getLength());
		return shows.isEmpty();
	}

	private boolean canDeleteShow(int idShow) {
		List<Reservation> reservations = reservationDao.getShowsReservation(idShow);
		return reservations.isEmpty();
	}

	// GETTERS AND SETTERS

	public Show getShow() {
		return show;
	}

	public MovieBean getMovieBean() {
		return movieBean;
	}

	public void setMovieBean(MovieBean movieBean) {
		this.movieBean = movieBean;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	public ShowDao getShowDao() {
		return showDao;
	}

	public void setShowDao(ShowDao showDao) {
		this.showDao = showDao;
	}

	public List<Show> getShows() {
		return shows;
	}

	public void setShows(List<Show> shows) {
		this.shows = shows;
	}

	public MonitorBean getMonitorBean() {
		return monitorBean;
	}

	public void setMonitorBean(MonitorBean monitorBean) {
		this.monitorBean = monitorBean;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

}

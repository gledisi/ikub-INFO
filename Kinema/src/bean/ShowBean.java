
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
import entitete.Show;

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
		this.showDao =new ShowDao();
		this.reservationDao=ReservationDao.INSTANCE;
        refreshBean();
    }

	public void refreshBean() {

		this.show = new Show();
		this.shows = showDao.getAllShows();
		
	}

	public String addShow() throws ParseException {

		Show showAdd = new Show();
		showAdd.setMovie(movieBean.getMovie());
		showAdd.setMonitori(monitorBean.getMonitor());
		showAdd.setData(dateTime);
		showAdd.setOra(dateTime);
		
		showDao.add(showAdd);
		
		return null;
	}

	public void deleteShow(int idShow) {
		if(reservationDao.getShowsReservation(idShow).isEmpty()) {
		showDao.delete(idShow);
		}else {
			System.out.println("Show has reservation cant delete");
		}
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

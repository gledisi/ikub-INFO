
package bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.ShowDao;
import entitete.Show;

@ManagedBean
@ViewScoped
public class ShowBean {

	private Show show;
	private ShowDao showDao;
	private List<Show> shows;
	private List<String> movies;
	private List<String> monitors;

	public ShowBean() {
		super();
		this.showDao = new ShowDao();
		refreshList();

	}

	public void refreshList() {

		this.show = new Show();
		this.shows = new ArrayList<>();
		System.out.println("koheZgjatja" + show.getData());

		try {
			this.shows.addAll(showDao.getAllShows());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void createShow() {

		showDao.add(show);
		refreshList();

	}

	public void deleteShow(int idShow) {
		showDao.delete(idShow);
		refreshList();
	}

	// GETTERS AND SETTERS

	public Show getShow() {
		return show;
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

	public List<String> getMovies() {
		return movies;
	}

	public void setMovies(List<String> movies) {
		this.movies = movies;
	}

	public List<String> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<String> monitors) {
		this.monitors = monitors;
	}

}

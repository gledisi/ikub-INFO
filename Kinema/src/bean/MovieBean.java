package bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import dao.GenreDao;
import dao.MovieDao;
import dao.ReservationDao;
import dao.ShowDao;
import entitete.Movie;
import entitete.Show;
import utility.Messages;

@ManagedBean
@ViewScoped
public class MovieBean {

	private Movie movie;
	private MovieDao movieDao;
	private GenreDao genreDao;
	private ShowDao showDao;
	private ReservationDao reservationDao;
	private List<Movie> movies;
	private List<String> allGenres = new ArrayList<>();

	@PostConstruct
	public void init() {
		this.movieDao = MovieDao.INSTANCE;
		this.genreDao = GenreDao.INSTANCE;
		this.showDao = new ShowDao();
		this.reservationDao = ReservationDao.INSTANCE;
		// allGenres = genreDao.getAllGenres();
		allGenres.add("Aksion");

		refreshBean();
	}

	public void refreshBean() {

		this.movie = new Movie();
		this.movies = movieDao.getAllMovies();

	}

	public String addMovie() {

		Movie addMovie = createMovieObject();

		if (canAddMovie(addMovie.getTitle())) {
			if (movieDao.add(movie)) {
				Messages.addMessage("Filmi u shtua!");
			} else {
				Messages.addMessage("Filmi nuk u shtua!");
			}
		} else {
			Messages.addMessage("Filmi nuk mund te shtohet!Ekziston film me kete titull!");
		}

		refreshBean();

		return null;
	}

	public String deleteMovie(int idMovie) {
		if (canDeleteMovie(idMovie)) {
			if (movieDao.delete(idMovie)) {
				Messages.addMessage("Filmi u fshi!");
			} else {
				Messages.addMessage("Filmi nuk u fshi!");
			}
		} else {
			Messages.addMessage("Filmi nuk mund te fshihet!\nKa rezervime per kete film!!");
		}

		return null;
	}

	public String movieDetail() {
		FacesContext fc = FacesContext.getCurrentInstance();
		Map<String, String> params = fc.getExternalContext().getRequestParameterMap();
		int id = Integer.parseInt(params.get("movieId"));

		movie.setId(id);
		System.out.println(id);
		return "movieDetail?faces-redirect=true&includeViewParams=true";
	}

	private boolean canDeleteMovie(int idMovie) {
		boolean canDelete = true;
		List<Show> shows = showDao.getMoviesShow(idMovie);
		for (Show show : shows) {
			if (!reservationDao.getShowsReservation(show.getId()).isEmpty()) {
				canDelete = false;
			}
		}
		return canDelete;
	}

	private boolean canAddMovie(String title) {
		boolean isNull = true;
		Movie movie = movieDao.getMoviebyTitle(title);
		if (movie != null) {
			isNull = false;
		}
		return isNull;
	}

	public String getMovieById() {
		this.movie= movieDao.getMoviebyId(movie.getId());
		return null;
		}

	private Movie createMovieObject() {
		Movie newMovie = new Movie();
		newMovie.setTitle(movie.getTitle());
		newMovie.setGenre(movie.getGenre());
		newMovie.setImdb(movie.getImdb());
		newMovie.setLength(movie.getLength());
		newMovie.setStartDate(movie.getStartDate());
		newMovie.setEndDate(movie.getEndDate());
		newMovie.setStoryline(movie.getStoryline());
		newMovie.setShows(movie.getShows());

		return newMovie;
	}
	// GETTERS AND SETTERS

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public MovieDao getMovieDao() {
		return movieDao;
	}

	public void setMovieDao(MovieDao movieDao) {
		this.movieDao = movieDao;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	public GenreDao getGenreDao() {
		return genreDao;
	}

	public void setGenreDao(GenreDao genreDao) {
		this.genreDao = genreDao;
	}

	public List<String> getAllGenres() {
		return allGenres;
	}

	public void setAllGenres(List<String> allGenres) {
		this.allGenres = allGenres;
	}

}

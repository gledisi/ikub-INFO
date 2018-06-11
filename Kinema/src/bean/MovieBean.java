package bean;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.GenreDao;
import dao.MovieDao;
import dao.ReservationDao;
import dao.ShowDao;
import entitete.Movie;
import entitete.Show;

@ManagedBean
@ViewScoped
public class MovieBean {

	private Movie movie;
	private MovieDao movieDao;
	private GenreDao genreDao;
	private ShowDao showDao;
	private ReservationDao reservationDao;
	private List<Movie> movies;
	private List<String> allGenres=new ArrayList<>() ;

	
	@PostConstruct
    public void init() {
		this.movieDao = MovieDao.INSTANCE;
		this.genreDao = GenreDao.INSTANCE;
		this.showDao = new ShowDao();
		this.reservationDao = ReservationDao.INSTANCE;
		//allGenres = genreDao.getAllGenres();
		allGenres.add("Aksion");
		
        refreshBean();
    }
	
	public void refreshBean() {
		
		this.movie = new Movie();		
		this.movies = movieDao.getAllMovies();
		
	}

	public void createMovie() {
		
		movieDao.add(movie);
		
		refreshBean();
	}
	public void deleteMovie(int idMovie) {
		if(canDeleteMovie(idMovie)) {
		movieDao.delete(idMovie);
		}else {
			System.out.println("Movie has shows with reservation");
		}		
	}
	
	private boolean canDeleteMovie(int idMovie) {
		boolean canDelete = true;
		List<Show> shows = showDao.getMoviesShow(idMovie);
		for (Show show : shows) {
			if(!reservationDao.getShowsReservation(show.getId()).isEmpty()) {
				canDelete =false;
			}
		}
		return canDelete;
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

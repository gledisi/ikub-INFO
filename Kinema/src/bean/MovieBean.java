package bean;


import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.MovieDao;
import entitete.Movie;

@ManagedBean
@ViewScoped
public class MovieBean {

	private Movie movie;
	private MovieDao movieDao;
	private List<Movie> movies;
	private List<String> list ;

	public MovieBean() {
		super();
		this.movieDao = new MovieDao();
		refreshList();
		 list = new ArrayList<String>() {{
			    add("Aksion");
			    add("Drame");
			    add("Fantazi");
			}};
	}
	
	public void refreshList() {
		
		this.movie = new Movie();		
		this.movies = new ArrayList<>();
		System.out.println("koheZgjatja"+movie.getStartDate());
		
		try {
			this.movies.addAll(movieDao.getAllMovies());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void createMovie() {
		
		System.out.println("koheZgjatja"+movie.getStartDate());
		movieDao.add(movie);
		
		refreshList();
	}
	public void deleteMovie(int idMovie) {
		movieDao.delete(idMovie);
		refreshList();
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

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}

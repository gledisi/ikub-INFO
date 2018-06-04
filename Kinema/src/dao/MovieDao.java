package dao;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entitete.Movie;
import hibenate.HibernateUtil;

public class MovieDao {

	// Querys for Movie
	private static final String MOVIE_BY_ID = "FROM Movie WHERE id=:id";
	private static final String ALL_MOVIES = "from Movie";
	private static final String CURRENT_MOVIES = "FROM `Movie` WHERE `startDate`<=:today AND `endDate`>:today ";

	private SessionFactory sessionFactory;
	private Transaction trns = null;
	private Session session;

	public MovieDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean add(Movie movie) {
		session = sessionFactory.openSession();
		boolean isAdd = true;
		try {
			trns = session.beginTransaction();
			session.save(movie);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			isAdd = false;
		} finally {
			session.flush();
			session.close();
		}

		return isAdd;
	}

	public boolean update(Movie movie) {
		session = sessionFactory.openSession();
		boolean isUpdate = true;
		try {
			trns = session.beginTransaction();
			session.update(movie);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			isUpdate = false;
		} finally {
			session.flush();
			session.close();
		}

		return isUpdate;
	}

	public boolean delete(int idMovie) {
		session = sessionFactory.openSession();
		boolean isDelete = true;
		try {
			trns = session.beginTransaction();
			Movie movie = (Movie) session.load(Movie.class, new Integer(idMovie));
			session.delete(movie);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			isDelete = false;
		} finally {
			session.flush();
			session.close();
		}

		return isDelete;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> getAllMovies() {
		session = sessionFactory.openSession();
		List<Movie> allMovies = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(ALL_MOVIES);
			allMovies = (List<Movie>) criteria.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return allMovies;
	}

	public Movie getMovie(int idMovie) {
		session = sessionFactory.openSession();
		Movie movie = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(MOVIE_BY_ID);
			criteria.setParameter("id", idMovie);
			movie = (Movie) criteria.uniqueResult();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return movie;
	}

	@SuppressWarnings("unchecked")
	public List<Movie> currentMovies() {
		session = sessionFactory.openSession();
		List<Movie> movies = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(CURRENT_MOVIES);
			criteria.setParameter("today", LocalDate.now());
			movies = (List<Movie>) criteria.list();
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
		} finally {
			session.flush();
			session.close();
		}
		return movies;
	}

}

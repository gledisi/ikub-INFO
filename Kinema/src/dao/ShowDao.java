package dao;

import java.sql.Time;
import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entitete.Show;
import hibernate.HibernateUtil;

public class ShowDao {

	private static final String GET_SHOW_BY_ID = "FROM shows WHERE id=:id";
	private static final String GET_MONITORS_SHOW = "FROM Show WHERE idMonitori=:idMonitor";
	
	private static final String GET_MONITORS_SHOW_BY_TIME =
			"Select show FROM Show show,Movie movie "
			+ " WHERE ADDTIME(show.time,SEC_TO_TIME(movie.length*60))>=:thisTime and TIMEDIFF(show.time,SEC_TO_TIME(:thisLength*60))<=:thisTime";
	
	private static final String GET_MOVIES_SHOW = "FROM Show WHERE idMovie=:idMovie";
	private static final String GET_ALL_SHOWS = "from Show";

	private SessionFactory sessionFactory;
	private Transaction trns = null;
	private Session session;

	public ShowDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean add(Show show) {
		session = sessionFactory.openSession();
		boolean isAdd = true;
		try {
			trns = session.beginTransaction();
			session.save(show);
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

	public boolean update(Show show) {
		session = sessionFactory.openSession();
		boolean isUpdate = true;
		try {
			trns = session.beginTransaction();
			session.update(show);
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

	public boolean delete(int idShow) {
		session = sessionFactory.openSession();
		boolean isDelete = true;
		try {
			trns = session.beginTransaction();
			Show show = (Show) session.load(Show.class, new Integer(idShow));
			session.delete(show);
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
	public List<Show> getAllShows() {
		session = sessionFactory.openSession();
		List<Show> allShows = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_ALL_SHOWS);
			allShows = (List<Show>) criteria.list();
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
		return allShows;
	}

	@SuppressWarnings("unchecked")
	public List<Show> getMonitorsShow(int idMonitor) {
		session = sessionFactory.openSession();
		List<Show> shows = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_MONITORS_SHOW);
			criteria.setParameter("idMonitor", idMonitor);
			shows = (List<Show>) criteria.list();
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
		return shows;
	}

	@SuppressWarnings("unchecked")
	public List<Show> getMonitorsShowByTime(Date showDate, int movieLength) {

		Time time = new Time( showDate.getTime());
		
		session = sessionFactory.openSession();
		List<Show> shows = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_MONITORS_SHOW_BY_TIME);
			criteria.setParameter("thisTime", time);
			criteria.setParameter("thisLength", movieLength);
			shows = (List<Show>) criteria.list();
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
		return shows;
	}

	@SuppressWarnings("unchecked")
	public List<Show> getMoviesShow(int idMovie) {
		session = sessionFactory.openSession();
		List<Show> shows = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_MOVIES_SHOW);
			criteria.setParameter("idMovie", idMovie);
			shows = (List<Show>) criteria.list();
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
		return shows;
	}

	public Show getShow(int idShow) {
		session = sessionFactory.openSession();
		Show show = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_SHOW_BY_ID);
			criteria.setParameter("id", idShow);
			show = (Show) criteria.uniqueResult();
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
		return show;
	}

}

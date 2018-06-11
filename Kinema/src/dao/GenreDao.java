package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernate.HibernateUtil;

public enum GenreDao {
	INSTANCE;
	
	private static final String GET_ALL_GENRE = "from genre";
	private SessionFactory sessionFactory= HibernateUtil.getSessionFactory();
	private Transaction trns = null;
	private Session session;

	
	@SuppressWarnings("unchecked")
	public List<String> getAllGenres() {
		session = sessionFactory.openSession();
		List<String> allGenres = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_ALL_GENRE);
			allGenres = (List<String>) criteria.list();
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
		return allGenres;
	}
}

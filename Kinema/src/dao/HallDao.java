package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entitete.Hall;
import hibernate.HibernateUtil;

public class HallDao {

	
	private static final String GET_ALL_HALLS = "from Hall";
	private static final String GET_HALLS_BY_NAME = "from Hall Where name=:name";
	
	
	private SessionFactory sessionFactory;
	private Transaction trns = null;
	private Session session;

	public HallDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	
	public boolean addHall(Hall hall) {
		session = sessionFactory.openSession();
		try {
			trns = session.beginTransaction();
			session.save(hall);
			session.getTransaction().commit();
		} catch (RuntimeException e) {
			if (trns != null) {
				trns.rollback();
			}
			e.printStackTrace();
			return false;
		} finally {
			session.flush();
			session.close();
		}

		return true;
	}

	public boolean deleteHall(int idHall) {
		session = sessionFactory.openSession();
		boolean isDelete = true;
		try {
			trns = session.beginTransaction();
			Hall hall = (Hall) session.load(Hall.class, new Integer(idHall));
			session.delete(hall);
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
	public List<Hall> getAllHalls() {
		session = sessionFactory.openSession();
		List<Hall> allHalls = null;
		try { 
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_ALL_HALLS);
			allHalls = (List<Hall>) criteria.list();
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
		return allHalls;
	}
	
	@SuppressWarnings("unchecked")
	public List<Hall> getHallByName(String name) {
		session = sessionFactory.openSession();
		List<Hall> allHalls = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_HALLS_BY_NAME);
			criteria.setParameter("name", name);
			allHalls = (List<Hall>) criteria.list();
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
		return allHalls;
	}
	

}

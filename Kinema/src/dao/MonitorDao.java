package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entitete.Monitor;
import hibernate.HibernateUtil;

public class MonitorDao {

	private static final String GET_MONITOR_BY_ID = "FROM Monitor WHERE id=:id";
	private static final String GET_ALL_MONITORS = "from Monitor";
	private static final String GET_HALL_MONITORS = "from Monitor Where idHall=:idHall";
	private static final String COUNT_MONITORS = "select count(*) from Monitor Where idHall=:idHall";
	
	private SessionFactory sessionFactory;
	private Transaction trns = null;
	private Session session;

	public MonitorDao() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public boolean addMonitor(Monitor monitor) {
		session = sessionFactory.openSession();
		boolean isAdd = true;
		try {
			trns = session.beginTransaction();
			session.save(monitor);
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
	
	public boolean deleteMonitor(int idMonitor) {
		session = sessionFactory.openSession();
		boolean isDelete = true;
		try {
			trns = session.beginTransaction();
			Monitor monitor = (Monitor) session.load(Monitor.class, new Integer(idMonitor));
			session.delete(monitor);
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
	public List<Monitor> getAllMonitors() {
		session = sessionFactory.openSession();
		List<Monitor> allMonitors = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_ALL_MONITORS);
			allMonitors = (List<Monitor>) criteria.list();
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
		return allMonitors;
	}

	@SuppressWarnings("unchecked")
	public List<Monitor> getHallMonitors(int idHall) {
		session = sessionFactory.openSession();
		List<Monitor> hallMonitors = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_HALL_MONITORS);
			criteria.setParameter("idHall", idHall);
			hallMonitors = (List<Monitor>) criteria.list();
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
		return hallMonitors;
	}

	public Monitor getMonitor(int idMonitor) {
		session = sessionFactory.openSession();
		Monitor monitor = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(GET_MONITOR_BY_ID);
			criteria.setParameter("id", idMonitor);
			monitor = (Monitor) criteria.uniqueResult();
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
		return monitor;
	}
	

	public long countMonitors(int idHall) {
		session = sessionFactory.openSession();
		long nrMonitors = 0;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(COUNT_MONITORS);
			criteria.setParameter("idHall", idHall);
			nrMonitors = (long) criteria.uniqueResult();
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
		return nrMonitors;
	}

}

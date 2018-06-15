package dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entitete.Monitor;
import entitete.Reservation;
import hibernate.HibernateUtil;

public enum ReservationDao {

	INSTANCE;
	// Querys for Reservation
	private static final String ALL_RESERVATIONS = "from Reservation";
	private static final String SHOWS_RESERVATION = "FROM Reservation WHERE idShow=:idShow";
	private static final String USERS_RESERVATION = "FROM Reservation WHERE idUser=:idUser";

	private SessionFactory sessionFactory=HibernateUtil.getSessionFactory();;
	private Transaction trns = null;
	private Session session;


	public boolean addReservation(Reservation reservation) {
		session = sessionFactory.openSession();
		boolean isAdd = true;
		try {
			trns = session.beginTransaction();
			session.save(reservation);
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

	public boolean update(Reservation reservation) {
		session = sessionFactory.openSession();
		boolean isUpdate = true;
		try {
			trns = session.beginTransaction();
			session.update(reservation);
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

	public boolean delete(int idReservation) {
		session = sessionFactory.openSession();
		boolean isDelete = true;
		try {
			trns = session.beginTransaction();
			Reservation reservation = (Reservation) session.load(Reservation.class, new Integer(idReservation));
			session.delete(reservation);
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
	public List<Reservation> getAllReservations() {
		session = sessionFactory.openSession();
		List<Reservation> allReservations = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(ALL_RESERVATIONS);
			allReservations = (List<Reservation>) criteria.list();
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
		return allReservations;
	}

	@SuppressWarnings("unchecked")
	public List<Reservation> getShowsReservation(int idShow) {
		session = sessionFactory.openSession();
		List<Reservation> showsReservation = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(SHOWS_RESERVATION);
			criteria.setParameter("idShow", idShow);
			showsReservation = (List<Reservation>) criteria.list();
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
		return showsReservation;
	}
	
	@SuppressWarnings("unchecked")
	public List<Reservation> getUsersReservation(int idUser) {
		session = sessionFactory.openSession();
		List<Reservation> userReservation = null;
		try {
			trns = session.beginTransaction();
			Query criteria = session.createQuery(USERS_RESERVATION);
			criteria.setParameter("idUser", idUser);
			userReservation = (List<Reservation>) criteria.list();
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
		return userReservation;
	}

	public boolean deleteReservation(int idRes) {
		session = sessionFactory.openSession();
		boolean isDelete = true;
		try {
			trns = session.beginTransaction();
			Reservation reservation = (Reservation) session.load(Reservation.class, new Integer(idRes));
			session.delete(reservation);
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

}


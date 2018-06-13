package dao;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import entitete.User;
import hibernate.HibernateUtil;

public class UserDao {

	private SessionFactory sessionFactory;
	private Transaction trns = null;
    private Session session;
	
    public UserDao() {
    	sessionFactory = HibernateUtil.getSessionFactory();
    }
    
   
	public void add(User user) {
					
         session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.save(user);
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
	}

	
	public boolean edit(User user) {
		session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            session.update(user);
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
	
	
	public void delete(int userId) {
		session = sessionFactory.openSession();
        try {
            trns = session.beginTransaction();
            User user = (User) session.load(User.class, new Integer(userId));
            session.delete(user);
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
	}
	

	public User getUserFromId(int userId) {
		session = sessionFactory.openSession();
		User user=null;
        try {
            trns = session.beginTransaction();
            Query criteria = session.createQuery("FROM User WHERE id=:id");
        	criteria.setParameter("id", userId);
            user = (User) criteria.uniqueResult();
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
       return user; 
	}
	
	
	public User getLoggedUser(String email,String pass) {
		
		session = sessionFactory.openSession();
		User user=null;
        try {
            trns = session.beginTransaction();
            Query criteria = session.createQuery("FROM User WHERE email=:email And password=:pass");
        	criteria.setParameter("email", email);
        	criteria.setParameter("pass", pass);
            user = (User) criteria.uniqueResult();
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
       return user;
		
	}

	
	public User getLoggedUser(String email) {
		
		session = sessionFactory.openSession();
		User user=null;
        try {
            trns = session.beginTransaction();
            Query criteria = session.createQuery("FROM User WHERE email=:email");
        	criteria.setParameter("email", email);
            user = (User) criteria.uniqueResult();
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
       return user;
		
	}


	
	public boolean changePassword(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}

package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.UserDao;
import entitete.Role;
import entitete.User;

@ManagedBean
@ViewScoped
public class UserCrudBean {

	private User user;
	private UserDao userDao;


	@PostConstruct
	public void init() {
		this.userDao = new UserDao();
		this.user = new User();
	}

	public String addUser() {
		Role roli = new Role();
		roli.setId(1);
		user.setRoli(roli);
		userDao.add(user);

		return "login.xhtml?faces-redirect=true";
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

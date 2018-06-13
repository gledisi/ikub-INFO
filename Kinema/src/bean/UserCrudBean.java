package bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import dao.UserDao;
import entitete.Role;
import entitete.User;
import utility.Messages;

@ManagedBean
@ViewScoped
public class UserCrudBean {

	private User user;
	private UserDao userDao;
	private String newPassword;
	private String confirmPassword;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

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

	public String editUser() {

		User editUser = userBean.getUser();
		userDao.edit(editUser);

		return null;
	}

	public String changePassword() {

		User editUser = userBean.getUser();
		if (this.user.getPassword().equals(userDao.getUserFromId(editUser.getId()).getPassword())) {
			if (this.confirmPassword.equals(this.newPassword)) {
				editUser.setPassword(this.newPassword);
				if (userDao.edit(editUser)) {
					Messages.addMessage("Passwordi u ndryshua!");
				} else {
					Messages.addMessage("Passwordi nuk ndryshua!");
				}
			} else {
				Messages.addMessage("Passwordi i ri dhe konfirmimi passwordit jane te ndryshem!");
			}
		} else {
			Messages.addMessage("Passwordi i pasakte!");
		}

		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}

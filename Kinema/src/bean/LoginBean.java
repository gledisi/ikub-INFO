package bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

import dao.UserDao;
import entitete.User;

@ManagedBean
@RequestScoped
public class LoginBean {

	private String email;
	private String password;

	@ManagedProperty(value = "#{userBean}")
	private UserBean userBean;

	private UserDao userDao = new UserDao();

	public String logIn() {

		// StrongPasswordEncryptor passwordEncryptor = new StrongPasswordEncryptor();
		User user = userDao.getLoggedUser(email, password);
		if (user != null) {
			userBean.setUser(user);
			String roli = user.getRoli().getRoli();
			System.out.println(roli);
			if (roli.equalsIgnoreCase("Admin")) {
				return "admin/home.xhtml?faces-redirect=true";

			} else  {
				return "admin/show.xhtml?faces-redirect=true";
			}
		}else {
			System.out.println("te dhena te pasakta");
		}

		return null;
	}

	public String logOut() {

		userBean.logOut();
		System.out.println("loging out");
		return "/login.xhtml?faces-redirect=true";
	}

	// GETTERS AND SETTERS

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

}

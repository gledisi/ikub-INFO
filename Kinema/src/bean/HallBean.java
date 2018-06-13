package bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import dao.HallDao;
import dao.MonitorDao;
import dao.ShowDao;
import entitete.Hall;
import entitete.Monitor;
import utility.Messages;

@ManagedBean
@ViewScoped
public class HallBean {

	private Hall hall;
	private HallDao hallDao;
	private MonitorDao monitorDao;
	private ShowDao showDao;
	private List<Hall> halls;

	@PostConstruct
	public void init() {
		this.hallDao = new HallDao();
		this.monitorDao = new MonitorDao();
		this.showDao = new ShowDao();
		refreshBean();
	}

	private void refreshBean() {

		this.hall = new Hall();
		this.halls = hallDao.getAllHalls();

	}

	public String addHall() {

		Hall addHall = new Hall();
		addHall.setName(hall.getName());
		if (canAddHall(addHall.getName())) {

			if (hallDao.addHall(addHall)) {
				Messages.addMessage("Salla u shtua!");
			} else {
				Messages.addMessage("Salla nuk u shtua!");
			}

			refreshBean();
		} else {
			Messages.addMessage("Salla me kete emer ekziston!");
		}

		return null;
	}

	public String deleteHall(int idHall) {
		
		if (canDeleteHall(idHall)) {

			if (hallDao.deleteHall(idHall)) {
				Messages.addMessage("Salla u fshi!");
			} else {
				Messages.addMessage("Salla nuk u fshi!");
			}
			
		} else {
			Messages.addMessage("Salla ka filmi qe po shfaqen!");
		}
		
		refreshBean();
		return null;
	}

	private boolean canAddHall(String name) {
		List<Hall> halls = hallDao.getHallByName(name);
		return halls.isEmpty();
	}

	private boolean canDeleteHall(int idHall) {
		boolean canDelete = true;
		List<Monitor> monitors = monitorDao.getHallMonitors(idHall);
		for (Monitor monitor : monitors) {
			if (!showDao.getMonitorsShow(monitor.getId()).isEmpty()) {
				canDelete = false;
			}
		}
		return canDelete;
	}

	// GETTERS AND SETTERS

	public Hall getHall() {
		return hall;
	}

	public void setHall(Hall hall) {
		this.hall = hall;
	}

	public HallDao getHallDao() {
		return hallDao;
	}

	public void setHallDao(HallDao hallDao) {
		this.hallDao = hallDao;
	}

	public List<Hall> getHalls() {
		return halls;
	}

	public void setHalls(List<Hall> halls) {
		this.halls = halls;
	}

}

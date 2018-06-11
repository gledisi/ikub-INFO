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
	
	public void addHall() {
		Hall addHall = new Hall();
		addHall.setName(hall.getName());
		hallDao.addHall(addHall);
		refreshBean();
	}
	
	public String deleteHall(int idHall) {
		if(canDeleteShow(idHall)) {
		hallDao.deleteHall(idHall);
		}else {
			System.out.println("This hall has monitors with show");
		}
		return null;		
	}
	
	private boolean canDeleteShow(int idHall) {
		boolean canDelete = true;
		List<Monitor> monitors = monitorDao.getHallMonitors(idHall);
		for (Monitor monitor : monitors) {
			if(!showDao.getMonitorsShow(monitor.getId())) {
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

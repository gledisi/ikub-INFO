package bean;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.view.ViewScoped;

import dao.MonitorDao;
import dao.ShowDao;
import entitete.Chair;
import entitete.Monitor;
import entitete.Show;
import utility.Messages;

@ManagedBean
@ViewScoped
public class MonitorBean {

	private Monitor monitor;
	private MonitorDao monitorDao;
	private ShowDao showDao;
	private List<Monitor> monitors;
	private List<Monitor> hallMonitors;

	private Integer rows;
	private Integer columns;

	@ManagedProperty(value = "#{hallBean}")
	private HallBean hallBean;

	@PostConstruct
	public void init() {
		this.monitorDao = new MonitorDao();
		this.showDao = new ShowDao();
		refreshBean();
	}

	private void refreshBean() {

		this.monitor = new Monitor();
		this.monitors = monitorDao.getAllMonitors();
	}
		
	

	public void getHallsMonitors(){
		this.hallMonitors = monitorDao.getHallMonitors(hallBean.getHall().getId());
	for (Monitor monitor : hallMonitors) {
		System.out.println(monitor.getId());
	}
		
}
	
	public String addMonitor() {
		Monitor addMonitor = new Monitor();

		Set<Chair> chairs = createChairs(addMonitor);
		addMonitor.setHall(hallBean.getHall());
		addMonitor.setName(monitor.getName());
		addMonitor.setChairs(chairs);

		if (canAddMonitor(addMonitor.getName())) {
			if (monitorDao.addMonitor(addMonitor)) {
				Messages.addMessage("Monitori u shtua!");
			} else {
				Messages.addMessage("Monitori nuk u shtua!");
			}
		} else {
			Messages.addMessage("Monitori me kete emer ekziston!");
		}

		refreshBean();
		
		return null;
	}

	public String deleteMonitor(int idMonitor) {
		if (canDeleteMonitor(idMonitor)) {
			if (monitorDao.deleteMonitor(idMonitor)) {
				Messages.addMessage("Monitori u fshi!");
			} else {
				Messages.addMessage("Monitori nuk u fshi!");

			}
		} else {
			Messages.addMessage("Ne kete monitor po shfaqen filma!");
		}
		
		refreshBean();
		
		return null;
	}

	public long countMonitors(int idHall) {
		return monitorDao.countMonitors(idHall);
	}

	public long countChairs(int idMonitor) {
		return monitorDao.countChairs(idMonitor);
	}

	private Set<Chair> createChairs(Monitor addMonitor) {
		Chair chair = null;
		Set<Chair> chairs = new HashSet<>();
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				chair = new Chair();
				chair.setRow(i + 1);
				chair.setCol(j + 1);
				chair.setMonitor(addMonitor);
				chairs.add(chair);
			}
		}
		return chairs;
	}

	public boolean canAddMonitor(String name) {
		boolean isNull = true;
		Monitor monitor = monitorDao.getMonitorByName(name);
		if (monitor != null) {
			isNull = false;
		}
		return isNull;
	}

	public boolean canDeleteMonitor(int idMonitor) {
		List<Show> shows = showDao.getMonitorsShow(idMonitor);
		return shows.isEmpty();
	}

	// GETTERS AND SETTERS

	public Monitor getMonitor() {
		return monitor;
	}

	public List<Monitor> getHallMonitors() {
		return hallMonitors;
	}

	public void setHallMonitors(List<Monitor> hallMonitors) {
		this.hallMonitors = hallMonitors;
	}

	public void setMonitor(Monitor monitor) {
		this.monitor = monitor;
	}

	public MonitorDao getMonitorDao() {
		return monitorDao;
	}

	public void setMonitorDao(MonitorDao monitorDao) {
		this.monitorDao = monitorDao;
	}

	public List<Monitor> getMonitors() {
		return monitors;
	}

	public void setMonitors(List<Monitor> monitors) {
		this.monitors = monitors;
	}

	public HallBean getHallBean() {
		return hallBean;
	}

	public void setHallBean(HallBean hallBean) {
		this.hallBean = hallBean;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}

	public Integer getColumns() {
		return columns;
	}

	public void setColumns(Integer columns) {
		this.columns = columns;
	}

	public ShowDao getShowDao() {
		return showDao;
	}

	public void setShowDao(ShowDao showDao) {
		this.showDao = showDao;
	}

}

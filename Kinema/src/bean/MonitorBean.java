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

@ManagedBean
@ViewScoped
public class MonitorBean {

	private Monitor monitor;
	private MonitorDao monitorDao;
	private ShowDao showDao;
	private List<Monitor> monitors;
	private List<Monitor> hallMonitors;

	private int rows;
	private int columns;

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

	public void addMonitor() {
		Monitor addMonitor = new Monitor();

		Set<Chair> chairs = createChairs(addMonitor);
		addMonitor.setHall(hallBean.getHall());
		addMonitor.setName(monitor.getName());
		addMonitor.setChairs(chairs);

		monitorDao.addMonitor(addMonitor);
		refreshBean();
	}

	public void deleteMonitor(int idMonitor) {
		if (showDao.getMonitorsShow(idMonitor)) {
			monitorDao.deleteMonitor(idMonitor);
			System.out.println("S'ka shfaqje");
		}else {
			System.out.println("Shfaqjet e monitorit"+monitor.getShows());
			System.out.println("Ky monitor ka shfaqje");
		}		

	}

	public long countMonitors(int idHall) {
		return monitorDao.countMonitors(idHall);
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

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	public ShowDao getShowDao() {
		return showDao;
	}

	public void setShowDao(ShowDao showDao) {
		this.showDao = showDao;
	}
	

}

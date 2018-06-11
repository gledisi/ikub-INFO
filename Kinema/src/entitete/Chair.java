package entitete;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "chair")
public class Chair {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int row;
	@Column
	private int col;
	
	@ElementCollection
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="idMonitor", nullable=false)
	private Monitor monitor;
	
	@OneToMany(mappedBy = "chair")
	private Set<Ticket> tickets = new HashSet<>();

		

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public Set<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Monitor getMonitor() {
		return monitor;
	}
	public void setMonitor(Monitor monitori) {
		this.monitor = monitori;
	}
	
	
		
}

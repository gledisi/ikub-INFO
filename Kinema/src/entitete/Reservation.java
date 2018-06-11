package entitete;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "reservation")
public class Reservation {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int cmimi;
	@Temporal(TemporalType.DATE)
	private Date dataRezervimit;
	
	@ElementCollection
	@ManyToOne
    @JoinColumn(name="idShow", nullable=false)
	private Show show;
	@ElementCollection
	@ManyToOne
    @JoinColumn(name="idUser", nullable=false)
	private User user;
	
	@OneToMany(mappedBy = "reservation")
	private Set<Ticket> tickets = new HashSet<>();
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCmimi() {
		return cmimi;
	}
	public void setCmimi(int cmimi) {
		this.cmimi = cmimi;
	}

	public Date getDataRezervimit() {
		return dataRezervimit;
	}
	public void setDataRezervimit(Date dataRezervimit) {
		this.dataRezervimit = dataRezervimit;
	}
	public Set<Ticket> getTickets() {
		return tickets;
	}
	public void setTickets(Set<Ticket> tickets) {
		this.tickets = tickets;
	}
	public Show getShow() {
		return show;
	}
	public void setShow(Show show) {
		this.show = show;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}

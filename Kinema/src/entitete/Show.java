package entitete;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;


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
@Table(name = "shows")
public class Show {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Temporal(TemporalType.DATE)
	private Date data;
	@Temporal(TemporalType.TIME)
	private Date ora;
	
	@OneToMany(mappedBy = "show")
	private Set<Reservation> reservations = new HashSet<>();
	
	@ElementCollection
	@ManyToOne
    @JoinColumn(name="idMovie", nullable=false)
	private Movie movie;
	@ElementCollection
	@ManyToOne
    @JoinColumn(name="idMonitori", nullable=false)
	private Monitor monitori;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Date getOra() {
		return ora;
	}
	public void setOra(Date ora) {
		this.ora = ora;
	}
	public Set<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public Monitor getMonitori() {
		return monitori;
	}
	public void setMonitori(Monitor monitori) {
		this.monitori = monitori;
	}
	
	
	
}

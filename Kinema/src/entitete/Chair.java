package entitete;

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

@Entity
@Table(name = "chair")
public class Chair {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column
	private int rreshti;
	@Column
	private int kolona;
	
	@ElementCollection
	@ManyToOne
    @JoinColumn(name="idMonitori", nullable=false)
	private Monitor monitori;
	
	@OneToMany(mappedBy = "chair")
	private Set<Ticket> tickets = new HashSet<>();

		

	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getRreshti() {
		return rreshti;
	}
	public void setRreshti(int rreshti) {
		this.rreshti = rreshti;
	}
	public int getKolona() {
		return kolona;
	}
	public void setKolona(int kolona) {
		this.kolona = kolona;
	}
	public Monitor getMonitori() {
		return monitori;
	}
	public void setMonitori(Monitor monitori) {
		this.monitori = monitori;
	}
	
	
		
}

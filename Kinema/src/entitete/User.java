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
@Table(name = "user")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
	private int id;
	@Column
	private String emer;
	@Column
	private String mbiemer;
	@Column
	private String email;
	@Column
	private String password;
	@ElementCollection
	@ManyToOne
    @JoinColumn(name="idRoli", nullable=false)
	private Role roli;
	
	@OneToMany(mappedBy = "user")
	private Set<Reservation> reservations = new HashSet<>();
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmer() {
		return emer;
	}
	public void setEmer(String emer) {
		this.emer = emer;
	}
	public String getMbiemer() {
		return mbiemer;
	}
	public void setMbiemer(String mbiemer) {
		this.mbiemer = mbiemer;
	}
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
	public Role getRoli() {
		return roli;
	}
	public void setRoli(Role roli) {
		this.roli = roli;
	}
	public Set<Reservation> getReservations() {
		return reservations;
	}
	public void setReservations(Set<Reservation> reservations) {
		this.reservations = reservations;
	}	
	
}

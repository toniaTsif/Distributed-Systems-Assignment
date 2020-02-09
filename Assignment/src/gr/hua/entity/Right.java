package gr.hua.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import gr.hua.dao.PersonDAO;

@Entity
@Table(name = "Pright")
@IdClass(RightPk.class)
public class Right implements Serializable{
	
	@Id
	@Column(name = "role")
	private String role;
	
	@Id
	@Column(name = "service")
	private String service;
	
	@Column(name = "pright")
	private boolean right;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "person_right",
			joinColumns = {@JoinColumn(name = "right_role"), @JoinColumn(name = "right_service")},
			inverseJoinColumns = @JoinColumn(name = "person_id"))
	private List<Person> persons;
	
	/*@Autowired
	private PersonDAO persondao;*/
	
	public Right() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Right(String role, String service, boolean right) {
		super();
		this.role = role;
		this.service = service;
		this.right = right;
		//this.persons = persondao.getPersonsByRole(role);
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getService() {
		return service;
	}

	public void setService(String service) {
		this.service = service;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}

	@Override
	public String toString() {
		return "Right [role=" + role + ", service=" + service + ", right=" + right + "]";
	}
	
}

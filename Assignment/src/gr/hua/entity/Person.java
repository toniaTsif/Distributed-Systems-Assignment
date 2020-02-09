package gr.hua.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;

import gr.hua.dao.RightDAO;

@Entity
@Table(name = "person")
public class Person implements Serializable{
	
	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "department")
	private String department;
	
	@Column(name = "enabled")
	private boolean enabled;
	
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private Student student;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "person_right",
			joinColumns = @JoinColumn(name = "person_id"),
			inverseJoinColumns = {@JoinColumn(name = "right_role"), @JoinColumn(name = "right_service")})
	private List<Right> rights;
	
	/*@Autowired
	private RightDAO rightdao;*/
	
	public Person() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Person(String name, String role, String password, int id, String department) {
		super();
		this.name = name;
		this.role = role;
		this.password = password;
		this.id = id;
		this.department = department;
		this.enabled = true;
	}
	
	public Person(String name, String role, String password, int id, String department, Student student) {
		super();
		this.name = name;
		this.role = role;
		this.password = password;
		this.id = id;
		this.department = department;
		this.enabled = true;
		this.student = student;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public List<Right> getRights() {
		return rights;
	}

	public void setRights(List<Right> rights) {
		this.rights = rights;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", password=" + password + ", role=" + role + ", department="
				+ department + ", enabled=" + enabled + "]";
	}
	
	
}

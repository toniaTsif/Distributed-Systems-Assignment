package gr.hua.entity;

import java.io.Serializable;
import java.util.List;

public class Right implements Serializable{
	
	private String role;
	private String service;
	private boolean right;
	private List<Person> persons;
	
	public Right() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Right(String role, String service, boolean right) {
		super();
		this.role = role;
		this.service = service;
		this.right = right;
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

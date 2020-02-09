package gr.hua.entity;

import java.io.Serializable;

public class RightPk implements Serializable{
	
	private String role;
	private String service;
	
	public RightPk() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RightPk(String role, String service) {
		super();
		this.role = role;
		this.service = service;
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

	@Override
	public String toString() {
		return "RightPk [role=" + role + ", service=" + service + "]";
	}
	
	
}

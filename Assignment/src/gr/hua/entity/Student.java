package gr.hua.entity;

import javax.persistence.*;

@Entity
@Table(name = "student")
public class Student {
	
	@Id
	@Column(name = "student_id")
	private int student_id;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "differentCity")
	private boolean differentCity;
	
	@Column(name = "yearsOfStudies")
	private int yearsOfStudies;
	
	@Column(name = "yearsOfAccomodation")
	private int yearsOfAccomodation;
	
	@Column(name = "siblings")
	private int siblings;
	
	@Column(name = "unemployedParents")
	private boolean unemployedParents;
	
	@Column(name = "income")
	private int income;
	
	@Column(name = "ranking")
	private int ranking;
	
	@Column(name = "approval")
	private boolean approval;
	
	@Column(name = "reviewed")
	private boolean reviewed;
	
	@Column(name = "accepted")
	private boolean accepted;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "student_id")
	private Person person;
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(int student_id, String email, boolean differentCity, int yearsOfStudies, int yearsOfAccomodation,
			int siblings, boolean unemployeedParents, int income, int ranking, boolean approval, boolean reviewed) {
		super();
		this.student_id = student_id;
		this.email = email;
		this.differentCity = differentCity;
		this.yearsOfStudies = yearsOfStudies;
		this.yearsOfAccomodation = yearsOfAccomodation;
		this.siblings = siblings;
		this.unemployedParents = unemployeedParents;
		this.income = income;
		this.ranking = ranking;
		this.approval = approval;
		this.reviewed = reviewed;
	}
	
	public Student(int student_id, String email, boolean differentCity, int yearsOfStudies, int yearsOfAccomodation,
			int siblings, boolean unemployeedParents, int income, int ranking, boolean approval, boolean reviewed, Person person) {
		super();
		this.student_id = student_id;
		this.email = email;
		this.differentCity = differentCity;
		this.yearsOfStudies = yearsOfStudies;
		this.yearsOfAccomodation = yearsOfAccomodation;
		this.siblings = siblings;
		this.unemployedParents = unemployeedParents;
		this.income = income;
		this.ranking = ranking;
		this.approval = approval;
		this.reviewed = reviewed;
		this.person = person;
	}
	
	public Student(int student_id, String email, boolean differentCity, int yearsOfStudies, int yearsOfAccomodation,
			int siblings, boolean unemployeedParents, int income, boolean approval, boolean reviewed) {
		super();
		this.student_id = student_id;
		this.email = email;
		this.differentCity = differentCity;
		this.yearsOfStudies = yearsOfStudies;
		this.yearsOfAccomodation = yearsOfAccomodation;
		this.siblings = siblings;
		this.unemployedParents = unemployeedParents;
		this.income = income;
		this.ranking = 0;
		this.approval = approval;
		this.reviewed = reviewed;
		this.accepted = false;
	}

	public int getStudent_id() {
		return student_id;
	}

	public void setStudent_id(int student_id) {
		this.student_id = student_id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isDifferentCity() {
		return differentCity;
	}

	public void setDifferentCity(boolean differentCity) {
		this.differentCity = differentCity;
	}

	public int getYearsOfStudies() {
		return yearsOfStudies;
	}

	public void setYearsOfStudies(int yearsOfStudies) {
		this.yearsOfStudies = yearsOfStudies;
	}

	public int getYearsOfAccomodation() {
		return yearsOfAccomodation;
	}

	public void setYearsOfAccomodation(int yearsOfAccomodation) {
		this.yearsOfAccomodation = yearsOfAccomodation;
	}

	public int getSiblings() {
		return siblings;
	}

	public void setSiblings(int siblings) {
		this.siblings = siblings;
	}

	public int getIncome() {
		return income;
	}

	public void setIncome(int income) {
		this.income = income;
	}

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public boolean isReviewed() {
		return reviewed;
	}

	public void setReviewed(boolean reviewed) {
		this.reviewed = reviewed;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
	
	public boolean isUnemployedParents() {
		return unemployedParents;
	}

	public void setUnemployedParents(boolean unemployedParents) {
		this.unemployedParents = unemployedParents;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public void generateRank() {
		if (yearsOfAccomodation < 4) {
			if (income == 0 && unemployedParents == true) {
				this.ranking = 10000;
			} else {
				if (income < 10000) {
					this.ranking += 100;
				}
				if (income > 10000 && income <15000) {
					this.ranking += 30;
				}
				
				this.ranking += (siblings*20);
				
				if (differentCity) {
					this.ranking += 50;
				}
				
				if (yearsOfStudies > 4) {
					this.ranking = -10000;
				}
				
				this.ranking += (-yearsOfAccomodation*10);
			} 
		} else {
			this.ranking = -10000;
		}
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", email=" + email + ", differentCity=" + differentCity
				+ ", yearsOfStudies=" + yearsOfStudies + ", yearsOfAccomodation=" + yearsOfAccomodation + ", siblings="
				+ siblings + ", unemployeedParents=" + unemployedParents + ", income=" + income + ", ranking="
				+ ranking + ", approval=" + approval + ", reviewed=" + reviewed + "]";
	}
	
	
}

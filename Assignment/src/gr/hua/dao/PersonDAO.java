package gr.hua.dao;

import java.util.ArrayList;
import java.util.List;

import gr.hua.entity.Person;

public interface PersonDAO {
	public void savePerson(Person person);
	public ArrayList<Person> getPersons();
	public Person getPersonById(int id);
	public void deletePerson(Person person);
	public void updatePerson(Person person);
	public List<Person> getPersonsByRole(String role);
	public List<Person> getDepartmentStudents (String department);
}

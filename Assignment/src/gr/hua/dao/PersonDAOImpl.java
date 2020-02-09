package gr.hua.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gr.hua.entity.Person;
import gr.hua.entity.Right;

@Repository
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private RightDAO rightdao;
	private Session currentSession;

	@Override
	@Transactional
	public void savePerson(Person person) {
		currentSession = sessionFactory.getCurrentSession();
		List<Right> rights = rightdao.getRightsByRole(person.getRole());
		if (!rights.isEmpty()) {
			person.setRights(rights);
		}
		currentSession.save(person);
	}

	@Override
	@Transactional
	public ArrayList<Person> getPersons() {
		currentSession = sessionFactory.getCurrentSession();
		ArrayList<Person> persons = (ArrayList<Person>) currentSession.createQuery("from Person").getResultList();
		return persons;
	}

	@Override
	@Transactional
	public Person getPersonById(int id) {
		currentSession = sessionFactory.getCurrentSession();
		Person person = currentSession.get(Person.class, id);
		return person;
	}

	@Override
	@Transactional
	public void deletePerson(Person person) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(person);
	}

	@Override
	@Transactional
	public void updatePerson(Person person) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.update(person);
	}

	@Override
	@Transactional
	public List<Person> getPersonsByRole(String role) {
		currentSession = sessionFactory.getCurrentSession();
		List<Person> persons = currentSession.createQuery("from Person where role='" + role + "'").getResultList();
		return persons;
	}

	@Override
	@Transactional
	public List<Person> getDepartmentStudents(String department) {
		currentSession = sessionFactory.getCurrentSession();
		List<Person> persons = currentSession
				.createQuery("from Person where role='ROLE_STUDENT' and department='" + department + "'").getResultList();
		return persons;
	}

}

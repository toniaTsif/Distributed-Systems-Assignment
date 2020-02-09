package gr.hua.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gr.hua.entity.Person;
import gr.hua.entity.Right;
import gr.hua.entity.RightPk;

@Repository
public class RightDAOImpl implements RightDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	private Session currentSession;
	@Autowired
	private PersonDAO persondao;

	@Override
	@Transactional
	public void saveRight(Right right) {
		currentSession = sessionFactory.getCurrentSession();
		List<Person> persons = persondao.getPersonsByRole(right.getRole());
		if (!persons.isEmpty()) {
			right.setPersons(persons);
		}
		currentSession.save(right);
	}

	@Override
	@Transactional
	public ArrayList<Right> getRights() {
		currentSession = sessionFactory.getCurrentSession();
		ArrayList<Right> rights = 
				(ArrayList<Right>) currentSession.createQuery("from Right").getResultList();
		return rights;
	}

	@Override
	@Transactional
	public Right getServiceByRole(RightPk id) {
		currentSession = sessionFactory.getCurrentSession();
		Right right = currentSession.get(Right.class, id);
		return right;
	}

	@Override
	@Transactional
	public void deleteRight(Right right) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(right);
	}

	@Override
	@Transactional
	public void updateRight(Right right) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.update(right);
	}

	@Override
	@Transactional
	public List<Right> getRightsByRole(String role) {
		currentSession = sessionFactory.getCurrentSession();
		List<Right> rights = currentSession.createQuery("from Right where role='" + role + "'").getResultList();
		return rights;
	}

}

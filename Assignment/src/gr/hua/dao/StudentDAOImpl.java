package gr.hua.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import gr.hua.entity.Person;
import gr.hua.entity.Student;

@Repository
public class StudentDAOImpl implements StudentDAO {

	@Autowired
	private SessionFactory sessionFactory;
	private Session currentSession;

	@Autowired
	private PersonDAO persondao;

	@Override
	@Transactional
	public void saveStudent(Student student) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.save(student);
	}

	@Override
	@Transactional
	public ArrayList<Student> getStudents() {
		currentSession = sessionFactory.getCurrentSession();
		ArrayList<Student> students = (ArrayList<Student>) currentSession.createQuery("from Student").getResultList();
		return students;
	}

	@Override
	@Transactional
	public Student getStudentById(int id) {
		currentSession = sessionFactory.getCurrentSession();
		Student student = currentSession.get(Student.class, id);
		return student;
	}

	@Override
	@Transactional
	public void deleteStudent(Student student) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.delete(student);
	}

	@Override
	@Transactional
	public void updateStudent(Student student) {
		currentSession = sessionFactory.getCurrentSession();
		currentSession.update(student);
	}

	@Override
	@Transactional
	public Student getPetition(String department) {
		currentSession = sessionFactory.getCurrentSession();
		List<Person> persons = persondao.getDepartmentStudents(department);
		if (persons != null) {
			Student student;
			for (Person person : persons) {
				student = person.getStudent();
				if (!student.isReviewed()) {
					return student;
				}
			}
		}
		return null;
	}

	@Override
	@Transactional
	public List<Student> studentsByRank() {
		currentSession = sessionFactory.getCurrentSession();
		ArrayList<Student> students = 
				(ArrayList<Student>) currentSession
				.createQuery("from Student where approval is true order by ranking desc").list();
		return students;
	}

}

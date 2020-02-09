package gr.hua.dao;

import java.util.ArrayList;
import java.util.List;

import gr.hua.entity.Student;

public interface StudentDAO {
	public void saveStudent(Student student);
	public ArrayList<Student> getStudents();
	public Student getStudentById(int id);
	public void deleteStudent(Student student);
	public void updateStudent(Student student);
	public Student getPetition(String department);
	public List<Student> studentsByRank();
}

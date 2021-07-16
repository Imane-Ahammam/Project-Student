package com.etudient.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

import com.etudient.model.Student;

@Component
public class StudentRepositoryJdbc{

	@Autowired
	private JdbcTemplate jdbcTemplate;


	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public void add(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("insert into student (nom,note) VALUES  (?, ?, ?);", student.getNom(), student.getNote());
	}

	public void update(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("update student set nom = ?, module =?, note = ? where `id` = ?;", student.getNom(), student.getNote(),student.getId());
		
	}

	public void delete(Student student) {
		// TODO Auto-generated method stub
		jdbcTemplate.update("delete from student where id = ?;", student.getId());
	}

	public List<Student> list() {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select * from student;", new ResultSetExtractor<List<Student>>() {

			public List<Student> extractData(ResultSet rs) throws SQLException {

				List<Student> students = new ArrayList<Student>();

				while (rs.next()) {

					Student student = new Student();
					
					student.setId(rs.getInt("id"));
					student.setNom(rs.getString("nom"));
					student.setNote(rs.getDouble("note"));
					students.add(student);

				}
				return students;
			}
		});
	}


	public Student find(int id) {
		// TODO Auto-generated method stub
		return jdbcTemplate.query("select id, nom, module, note from student where `id` = ?;", new Object[] { id }, new ResultSetExtractor<Student>() {

			public Student extractData(ResultSet rs) throws SQLException {

				Student student = new Student();
				while (rs.next()) {
					student.setId(rs.getInt("id"));
					student.setNom(rs.getString("nom"));
					student.setNote(rs.getDouble("note"));
				}
				return student;
			}
		});
	}

}

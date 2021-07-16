package com.etudient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.etudient.db.StudentRepositoryJdbc;
import com.etudient.model.Student;

@Controller
@RequestMapping("/")
public class StudentControler {

	@Autowired
	StudentRepositoryJdbc StudentRepositoryJdbc;
	
	@GetMapping("/list")

	public String index(Model model) {

		model.addAttribute("List", StudentRepositoryJdbc.list());

		return "List";
	}

	@GetMapping("/add")
	public String AddStudent(Model model) {

		Student student = new Student();

		model.addAttribute("student", student);

		return "AddStudent";
	}

	@PostMapping("/save")
	public String SaveStudent(@ModelAttribute Student student) {

		StudentRepositoryJdbc.add(student);

		return "redirect:/student/list";
	}

	@GetMapping("/update/{id}")
	public String UpdateStudent(@PathVariable(value = "id") int id, Model model)

	{
		Student student = StudentRepositoryJdbc.find(id);

		model.addAttribute("student", student);

		return "UpdateStudent";
	}

	@GetMapping("/delete/{id}")
	public String DeleteStudent(@PathVariable int id) {

		Student student = StudentRepositoryJdbc.find(id);

		StudentRepositoryJdbc.delete(student);

		return "redirect:/student/list";

	}
	
}

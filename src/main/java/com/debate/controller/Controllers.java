package com.debate.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.debate.entities.Students;
import com.debate.services.Services;

@Controller
@RequestMapping("/students")
public class Controllers {
	//autowire service class
	@Autowired
	private Services studentservice;
	
	@RequestMapping("/list")
	public String listStudents(Model model) {
		List<Students> students = studentservice.findAll();
		model.addAttribute("students", students);
		return "studentlist";
	}
	
	@RequestMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Students student = new Students();

		theModel.addAttribute("student", student);

		// send over to our form
		return "newform";
	}

	@RequestMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("id") int theId, Model theModel) {

	
		Students std = studentservice.findById(theId);

	
		theModel.addAttribute("student", std);

		// send over to our form
		return "newform";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("name") String name,
			@RequestParam("department") String department, @RequestParam("country") String country) {

		System.out.println(id);
		Students std;
		if (id != 0) {
			std = studentservice.findById(id);
			std.setName(name);
			std.setDepartment(department);
			std.setCountry(country);
		} else
			std = new Students(name, department, country);
		
		studentservice.save(std);

		// use a redirect to prevent duplicate submissions
		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") int theId) {

	
		studentservice.deleteById(theId);

		return "redirect:/students/list";

	}

	
	


}

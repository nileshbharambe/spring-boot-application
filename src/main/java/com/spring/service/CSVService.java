package com.spring.service;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.helper.CSVHelper;
import com.spring.model.Employee;

@Service
public class CSVService {

	public ByteArrayInputStream load() {

		Employee emp1 = new Employee();
		emp1.setId(101);
		emp1.setFirstName("Nilesh");
		emp1.setLastName("Bharambe");
		emp1.setPhoneNumber("9988770099");
		emp1.setEmailId("abc@gmail.com");
		emp1.setAddress("Pune");
		
		Employee emp2 = new Employee();
		emp2.setId(101);
		emp2.setFirstName("Pooja");
		emp2.setLastName("Bharambe");
		emp2.setPhoneNumber("9988770099");
		emp2.setEmailId("abc@gmail.com");
		emp2.setAddress("Pune");

		List<Employee> employee = new ArrayList<>();
		employee.add(emp1);
		employee.add(emp2);
		ByteArrayInputStream in = CSVHelper.employeeToCSV(employee);
		return in;
	}

	public List<Employee> listAll() {
		
		Employee emp1 = new Employee();
		emp1.setId(101);
		emp1.setFirstName("Nilesh");
		emp1.setLastName("Bharambe");
		emp1.setPhoneNumber("9988770099");
		emp1.setEmailId("abc@gmail.com");
		emp1.setAddress("Pune");
		
		Employee emp2 = new Employee();
		emp2.setId(101);
		emp2.setFirstName("Pooja");
		emp2.setLastName("Bharambe");
		emp2.setPhoneNumber("9988770099");
		emp2.setEmailId("abc@gmail.com");
		emp2.setAddress("Pune");

		List<Employee> employee = new ArrayList<>();
		employee.add(emp1);
		employee.add(emp2);
		
		return employee;
	}
}

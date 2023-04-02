package com.spring.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.supercsv.io.CsvBeanWriter;
import org.supercsv.io.ICsvBeanWriter;
import org.supercsv.prefs.CsvPreference;

import com.opencsv.CSVWriter;
import com.spring.constant.Constant;
import com.spring.model.Employee;
import com.spring.service.CSVService;

@CrossOrigin("http://localhost:8081")
@Controller
@RequestMapping("/api")
public class EmployeeController {

	@Value("${spring.application.name}")
	String appName;

	@Autowired
	CSVService fileService;

	@Autowired
	private Environment env;
	
	@GetMapping("/")
	@ResponseStatus(HttpStatus.OK)
	public String viewHomePage(Model model) {
		System.out.println("appName: " + appName);
		model.addAttribute("appName", appName);
		return "index";
	}

	@GetMapping("/employee")
	public String getList(Model model) {
		List<Employee> listEmployees = fileService.listAll();
		model.addAttribute("listEmployees", listEmployees);
		return "employee";
	}

	@GetMapping("/employee/download")
	public ResponseEntity<Resource> getFile() {

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());

		String filename = "employee-" + currentDateTime + ".csv";
		InputStreamResource file = new InputStreamResource(fileService.load());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/csv")).body(file);
	}

	@GetMapping("/employee/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String fileName = "employee_" + currentDateTime + ".csv";
		String headerValue = "attachment; filename="+fileName;
		String[] csvHeader = { "Employee ID", "First Name", "Last Name", "Contact Number", "E-mail", "Address" };
		response.setHeader(headerKey, headerValue);
		List<Employee> listEmployees = fileService.listAll();

		// save csv file in Temp folder start
		try {
			File filePath = new File(env.getProperty(Constant.csvPath)); 
			 if(!filePath.exists()) {
				 filePath.mkdirs();
			 }
			FileWriter outputfile = new FileWriter(filePath+File.separator+fileName);
			// create CSVWriter object filewriter object as parameter
			CSVWriter writer = new CSVWriter(outputfile);
			writer.writeNext(csvHeader);
			// add data to csv
			for (Employee employee : listEmployees) {
				String[] emp = { String.valueOf(employee.getId()), employee.getFirstName(), employee.getLastName(),
						employee.getPhoneNumber(), employee.getEmailId(), employee.getAddress() };
				writer.writeNext(emp);
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		// save csv file in Temp folder end

		ICsvBeanWriter csvWriter = new CsvBeanWriter(response.getWriter(), CsvPreference.STANDARD_PREFERENCE);
		String[] nameMapping = { "id", "firstName", "lastName", "phoneNumber", "emailId", "address" };
		csvWriter.writeHeader(csvHeader);
		for (Employee employee : listEmployees) {
			csvWriter.write(employee, nameMapping);
		}
		csvWriter.close();
	}

}

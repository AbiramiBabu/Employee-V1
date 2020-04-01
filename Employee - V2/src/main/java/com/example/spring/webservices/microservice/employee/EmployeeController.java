package com.example.spring.webservices.microservice.employee;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepo;

	//Get all the employees
	
	@GetMapping(path = "V2/employee")
	public List<Employee> retrieveAllEmployees() {
		return employeeRepo.findAll();
	}

	// get one employee

	@GetMapping(path = "V2/employee/{id}")
	public Optional<Employee> retrieveEmployee(@PathVariable int id) {
		Optional<Employee> employee = employeeRepo.findById(id);
		if (!employee.isPresent()) {
			throw new EmployeeNotFoundException("id -" + id + "Not Found");
		}
		return employee;
	}

	// create an employee

	@PostMapping(path = "V2/employee")
	public ResponseEntity<Object> saveEmployee(@Valid @RequestBody Employee employee) {
		Employee createdEmployee = employeeRepo.save(employee);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(createdEmployee.getId()).toUri();
		return ResponseEntity.created(location).build();
	}

	// Update an employee

	@PutMapping(path = "V2/employee/{id}")
	public ResponseEntity<Object> updateEmployee(@Valid @PathVariable int id, @RequestBody Employee employee) {
		Optional<Employee> identifyEmployeeById = employeeRepo.findById(id);
		if (!identifyEmployeeById.isPresent()) {
			throw new EmployeeNotFoundException("id -" + id + "Not Found");
		}
		Employee updatedEmployee = null;
		if (identifyEmployeeById.isPresent()) {
			employee.setId(id);
			updatedEmployee = employeeRepo.save(employee);
		}
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("").buildAndExpand(updatedEmployee)
				.toUri();
		return ResponseEntity.created(location).build();
	}

	// Delete an employee

	@DeleteMapping(path = "V2/employee/{id}")
	public void deleteEmployee(@PathVariable int id) {
		employeeRepo.deleteById(id);
	}

}

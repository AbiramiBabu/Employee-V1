package com.example.spring.webservices.microservice.employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	// Create Employeee

	@PostMapping(path = "V1/employee")
	public int createEmployee(@RequestBody Employee employee) {
		String insert = "INSERT into employee (dept,contactNumber,expr) VALUES (?,?,?)";
		int result = jdbcTemplate.update(insert, employee.getDept(), employee.getContactNumber(), employee.getExpr());
		return result;
	}

	// Get all the employees

	@GetMapping(path = "V1/employee")
	public List<Employee> getEmployee() {
		String select = "SELECT * from employee";
		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
			public Employee mapRow(ResultSet result, int row) throws SQLException {
				Integer id = result.getInt("id");
				String dept = result.getString("dept");
				Integer contactNumber = result.getInt("contactNumber");
				Integer expr = result.getInt("expr");
				return new Employee(id, dept, contactNumber, expr);
			}
		};
		List<Employee> employee = jdbcTemplate.query(select, rowMapper);
		return employee;
	}

	// Get Employee By Id

	@GetMapping(path = "V1/employee/{id}")
	public Employee getEmployeeById(@PathVariable int id) {
		String selectById = "SELECT * from employee where id = " + id;
		RowMapper<Employee> rowMapper = new RowMapper<Employee>() {
			public Employee mapRow(ResultSet result, int row) throws SQLException {
				Integer id = result.getInt("id");
				String dept = result.getString("dept");
				Integer contactNumber = result.getInt("contactNumber");
				Integer expr = result.getInt("expr");
				return new Employee(id, dept, contactNumber, expr);
			}
		};
		Employee employee = jdbcTemplate.queryForObject(selectById, rowMapper);
		return employee;
	}

	// Update employee By Id

	@PutMapping(path = "V1/employee/{id}")
	public int updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		String update = "UPDATE  employee set dept = ?,contactNumber = ?,expr = ? where id = " + id;
		int result = jdbcTemplate.update(update, employee.getDept(), employee.getContactNumber(), employee.getExpr());
		if(result == 0) {
			throw new EmployeeNotFoundException("id -"+ id + "Not Found");
		}
		return result;
	}

	// Delete by Id

	@DeleteMapping(path = "V1/employee/{id}")
	public int deleteEmployee(@PathVariable int id) {
		String delete = "DELETE from employee where id = " + id;
		int result = jdbcTemplate.update(delete);
		if(result == 0) {
			throw new EmployeeNotFoundException("id -"+ id + "Not Found");
		}
		return result;
	}
}

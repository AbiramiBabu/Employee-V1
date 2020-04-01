package com.example.spring.webservices.microservice.employee;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Integer id;

	private String dept;
	private int expr;
	private int contactNumber;

	public Employee() {

	}

	public Employee(Integer id, String dept, int expr, int contactNumber) {
		this.id = id;
		this.dept = dept;
		this.expr = expr;
		this.contactNumber = contactNumber;
	}

	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", dept=" + dept + ", expr=" + expr + ", contactNumber=" + contactNumber + "]";
	}

	public int getExpr() {
		return expr;
	}

	public void setExpr(int expr) {
		this.expr = expr;
	}

	public int getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(int contactNumber) {
		this.contactNumber = contactNumber;
	}

}

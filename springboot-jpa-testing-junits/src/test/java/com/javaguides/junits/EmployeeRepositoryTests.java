package com.javaguides.junits;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeRepositoryTests {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	//Junit test for save employee
	
	@Test
	@Order(1)
	@Rollback(value = false)
	public void saveEmployeeTest() {
		Employee employee = new Employee();
		employee.setFirstName("uma");
		employee.setLastName("reddy");
		employee.setEmail("reddy.uma@gmail.com");
		
		employeeRepository.save(employee);
		
		Assertions.assertThat(employee.getId()).isGreaterThan(0);
				
	}
	
	@Test
	@Order(2)
	public void getEmployeesTest() {
		Employee employee = employeeRepository.findById(1L).get();
		Assertions.assertThat(employee.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(3)
	public void getListOfEmployeesTest() {
		List<Employee> employees=employeeRepository.findAll();
		Assertions.assertThat(employees.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(4)
	@Rollback(value = false)
	public void updateEmployeesTest() {
		Employee employee = employeeRepository.findById(1L).get();
		
		employee.setEmail("reddy.uma44@gmail.com");
		
		Employee employeeUpdated = employeeRepository.save(employee);
		Assertions.assertThat(employeeUpdated.getEmail()).isEqualTo("reddy.uma@gmail.com");
	}
	
	@Test
	@Order(5)
	@Rollback(value = false)
	public void deleteEmployeesTest() {
		Employee employee = employeeRepository.findById(1L).get();
		employeeRepository.delete(employee);
		Employee employee1= null;
		Optional<Employee> optionalEmployee = employeeRepository.findByEmail("reddy.uma44@gmail.com");
		
		if(optionalEmployee.isPresent()) {
			employee1=optionalEmployee.get();
		}
		Assertions.assertThat(employee1).isNull();
		
	}

}

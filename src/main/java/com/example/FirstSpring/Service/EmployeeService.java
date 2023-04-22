package com.example.FirstSpring.Service;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Repository.EmployeeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployees () {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(int id) throws Exception {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("getEmployee(): Incorrect ID!");
        }
        return employeeRepository.findById(id).get();
    }

    public void createEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void updateEmployee(int id, Employee employee) throws Exception {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("updateEmployee(): Incorrect ID!");
        }
        if (employee.getAge() < 18) {
            throw new Exception("Validation Error: Employee must be over 18!");
        }
        if (employee.getName() == null) {
            throw new Exception("Validation Error: Employee name field is mandatory!");
        }
        Employee employeeToUpdate = employeeRepository.getOne(id);
        employeeToUpdate.setSpouse(employee.getSpouse());
        employeeToUpdate.setCity(employee.getCity());
        employeeToUpdate.setName(employee.getName());
        employeeToUpdate.setAge(employee.getAge());
        employeeToUpdate.setAddresses(employee.getAddresses());
        employeeRepository.save(employeeToUpdate);
    }

    public void addAddressToEmployee(int id, Address address) throws Exception {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("addAddressToEmployee(): Incorrect ID!");
        }
        Employee employeeToAddTo = employeeRepository.getOne(id);
        List<Address> addresses = employeeToAddTo.getAddresses();
        addresses.add(address);
        employeeToAddTo.setAddresses(addresses);
        employeeRepository.save(employeeToAddTo);
    }

    public void deleteEmployee(int id) throws Exception {
        if (!employeeRepository.existsById(id)) {
            throw new Exception("deleteEmployee(): Incorrect ID!");
        }
        employeeRepository.delete(employeeRepository.getById(id));
    }

    public List<Employee> findEmployeeByAge(int age) {
        return employeeRepository.findByAgeGreaterThan(age);
    }

    public List<Employee> findEmployeeByAgeImpl(int age) {
        List<Employee> employeeList = new ArrayList<>();
        employeeList = employeeRepository.findAll().stream()
                .filter(s -> s.getAge() > age)
                .sorted(Comparator.comparingInt(Employee::getAge))
                .collect(Collectors.toList());
        return employeeList;
    }

    public List<EmployeeDTO> getComparison() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(employee.getId(), employee.getName(), employee.getCity(), employee.getAge()))
                .filter(employeeDTO -> employeeDTO.getAge() > 0)
                .sorted().collect(Collectors.toList());
    }

    public Page<Employee> findEmployeesWithPagination(int offset, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset,pageSize));
    }
}

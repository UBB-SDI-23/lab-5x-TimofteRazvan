package com.example.FirstSpring.Service;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Repository.AddressRepository;
import com.example.FirstSpring.Repository.EmployeeRepository;
import com.example.FirstSpring.Repository.SpouseRepository;
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
    @Autowired
    SpouseRepository spouseRepository;
    @Autowired
    AddressRepository addressRepository;

    public List<Employee> getAllEmployees () {
        return employeeRepository.findAll();
    }

    public List<EmployeeDTO> findAllEmployeesAndMap() {
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::mapEmployeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

    public EmployeeDTO findEmployeeDTO(int id) throws Exception {
        if(!employeeRepository.existsById(id)) {
            throw new Exception("findEmployeeProjectDTO(): Incorrect ID!");
        }
        return mapEmployeeToEmployeeDTO(employeeRepository.findById(id).get());
    }

    private EmployeeDTO mapEmployeeToEmployeeDTO(Employee employee) {
        EmployeeDTO employeeDTO = new EmployeeDTO();
        employeeDTO.setId(employee.getId());
        employeeDTO.setName(employee.getName());
        employeeDTO.setCity(employee.getCity());
        employeeDTO.setAge(employee.getAge());
        employeeDTO.setSpouse(employee.getSpouse().getId());
        employeeDTO.setAddresses(employee.getAddresses().size());
        return employeeDTO;
    }

    public void saveEmployee(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        employee.setName(employeeDTO.getName());
        employee.setCity(employeeDTO.getCity());
        employee.setAge(employeeDTO.getAge());
        if(spouseRepository.existsById(employeeDTO.getSpouse())) {
            employee.setSpouse(spouseRepository.getById(employeeDTO.getSpouse()));
        }
        employeeRepository.save(employee);
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
        return employeeRepository.findAll().stream()
                .filter(s -> s.getAge() > age)
                .sorted(Comparator.comparingInt(Employee::getAge))
                .collect(Collectors.toList());
    }

    public Page<EmployeeDTO> findEmployeesByAgePaginated(int age, int offset, int pageSize) {
        Page<Employee> employees = employeeRepository.findByAgeGreaterThan(age, PageRequest.of(offset, pageSize));
        return employees.map(this::mapEmployeeToEmployeeDTO);
    }

    public List<EmployeeDTO> getComparison() {
        return employeeRepository.findAll().stream()
                .map(employee -> new EmployeeDTO(employee.getId(), employee.getName(), employee.getCity(), employee.getAge()))
                .filter(employeeDTO -> employeeDTO.getAge() > 0)
                .sorted().collect(Collectors.toList());
    }
    public Page<EmployeeDTO> findEmployeesWithPagination(int offset, int pageSize) {
        Page<Employee> employees = employeeRepository.findAll(PageRequest.of(offset, pageSize));
        return employees.map(this::mapEmployeeToEmployeeDTO);
    }

    /*
    public Page<EmployeeDTO> findEmployeesWithPagination(int offset, int pageSize) {
        return employeeRepository.findAll(PageRequest.of(offset,pageSize));
        List<Employee> employees = employeeRepository.findAll();
        return employees.stream()
                .map(this::mapEmployeeToEmployeeDTO)
                .collect(Collectors.toList());
    }

     */
}

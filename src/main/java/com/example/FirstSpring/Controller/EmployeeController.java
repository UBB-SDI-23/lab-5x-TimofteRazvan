package com.example.FirstSpring.Controller;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    @GetMapping("/employees")
    public ArrayList<Integer> findAllEmployees() {
        List<Employee> employeeList = employeeService.getAllEmployees();
        ArrayList<Integer> idList = new ArrayList<>();
        for (Employee employee : employeeList) {
            idList.add(employee.getId());
        }
        return idList;
    }

    @GetMapping("/employees/page/{offset}/{pageSize}")
    private List<EmployeeDTO> getEmployeesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<EmployeeDTO> employees = employeeService.findEmployeesWithPagination(offset, pageSize);
        return employees.getContent();
    }

    //@RequestMapping(value = "/employees", method = RequestMethod.GET)
    @GetMapping("/employees-details")
    public List<Employee> findAllEmployeesDetails() {
        return employeeService.getAllEmployees();
    }

    //@RequestMapping(value = "/employees/{id}", method = RequestMethod.GET)
    @GetMapping("/employees/{id}")
    public EmployeeDTO findEmployeeDTO(@PathVariable int id) {
        try {
            return employeeService.findEmployeeDTO(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/employees/add-addresses/{id}")
    public void addAddressesToEmployee(@PathVariable int id, @RequestBody ArrayList<Address> addresses) {
        try {
            for(Address address : addresses) {
                employeeService.addAddressToEmployee(id, address);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //@RequestMapping(value = "/employees", method = RequestMethod.POST)
    @PostMapping("/employees")
    public void createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeService.saveEmployee(employeeDTO);
    }

    //@RequestMapping(value = "/employees/{id}", method = RequestMethod.PUT)
    @PutMapping("/employees/{id}")
    public void updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
        try {
            employeeService.updateEmployee(id, employee);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE)
    @DeleteMapping("/employees/{id}")
    public void deleteEmployee(@PathVariable int id) {
        try {
            employeeService.deleteEmployee(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/employees/filter/age/{age}/{offset}/{pageSize}")
    public List<EmployeeDTO> findEmployeeByAge(@PathVariable int age, @PathVariable int offset, @PathVariable int pageSize) {
        Page<EmployeeDTO> employees = employeeService.findEmployeesByAgePaginated(age, offset, pageSize);
        return employees.getContent();
    }

    @GetMapping("/employees/compare/age")
    public List<EmployeeDTO> getComparison() {
        return employeeService.getComparison();
    }

    @GetMapping("/employees/maxPage")
    public Long getMaxPage(){
        return employeeService.getEmployeeMaxPage();
    }

    @GetMapping("/employees/maxPage/age/{age}")
    public Integer getMaxOlderThan(@PathVariable int age) {
        return employeeService.getEmployeesMaxOlderThan(age);
    }
}

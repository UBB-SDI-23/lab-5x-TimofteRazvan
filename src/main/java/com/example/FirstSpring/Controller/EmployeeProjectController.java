package com.example.FirstSpring.Controller;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
@CrossOrigin("http://localhost:3000")
public class EmployeeProjectController {
    @Autowired
    EmployeeProjectService employeeProjectService;

    @GetMapping("/employees-projects")
    public List<EmployeeProject> findAllEmployeeProjects() {
        return employeeProjectService.findAllEmployeeProjects();
    }

    @GetMapping("/employees-projects/page/{offset}/{pageSize}")
    private List<EmployeeProjectDTO> getEmployeeProjectsWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<EmployeeProjectDTO> employeeProjects = employeeProjectService.findEmployeeProjectsWithPagination(offset, pageSize);
        return employeeProjects.getContent();
    }

    @GetMapping("/employees-projects/{id}")
    public EmployeeProjectDTO findEmployeeProjectDTO(@PathVariable int id) {
        try {
            return employeeProjectService.findEmployeeProjectDTO(id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/employees-projects")
    public void createEmployeeProject(@RequestBody EmployeeProjectDTO employeeProjectDTO) {
        employeeProjectService.saveEmployeeProject(employeeProjectDTO);
    }

    @DeleteMapping("/employees-projects/{id}")
    public void deleteEmployeeProject(@PathVariable int id) {
        try {
            employeeProjectService.deleteEmployeeProject(id);
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/employees-projects/maxPage")
    public Long getMaxPage(){
        return employeeProjectService.getEmployeeProjectMaxPage();
    }
}

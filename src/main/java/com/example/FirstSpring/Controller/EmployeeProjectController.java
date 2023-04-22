package com.example.FirstSpring.Controller;

import com.example.FirstSpring.Entity.APIResponse;
import com.example.FirstSpring.Entity.EmployeeProject;
import com.example.FirstSpring.Entity.Project;
import com.example.FirstSpring.Service.EmployeeProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@ResponseBody
public class EmployeeProjectController {
    @Autowired
    EmployeeProjectService employeeProjectService;

    @GetMapping("/employees-projects")
    public List<EmployeeProject> findAllEmployeeProjects() {
        return employeeProjectService.findAllEmployeeProjects();
    }

    @GetMapping("/employees-projects/page/{offset}/{pageSize}")
    private APIResponse<Page<EmployeeProject>> getSpousesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<EmployeeProject> employeeProjects = employeeProjectService.findEmployeeProjectsWithPagination(offset, pageSize);
        return new APIResponse<>(employeeProjects.getSize(), employeeProjects);
    }

    @PostMapping("/employees-projects")
    public void createEmployeeProject(@RequestBody EmployeeProject employeeProject) {
        employeeProjectService.createEmployeeProject(employeeProject);
    }
}

package com.example.FirstSpring.Service;

import com.example.FirstSpring.Entity.EmployeeProject;
import com.example.FirstSpring.Entity.Spouse;
import com.example.FirstSpring.Repository.EmployeeProjectRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class EmployeeProjectService {
    @Autowired
    EmployeeProjectRepository employeeProjectRepository;

    public List<EmployeeProject> findAllEmployeeProjects() {
        return employeeProjectRepository.findAll();
    }

    public void createEmployeeProject(EmployeeProject employeeProject) {
        employeeProjectRepository.save(employeeProject);
    }

    public Page<EmployeeProject> findEmployeeProjectsWithPagination(int offset, int pageSize) {
        return employeeProjectRepository.findAll(PageRequest.of(offset,pageSize));
    }
}

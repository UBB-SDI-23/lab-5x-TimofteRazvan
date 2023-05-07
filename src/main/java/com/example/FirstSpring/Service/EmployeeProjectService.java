package com.example.FirstSpring.Service;

import com.example.FirstSpring.Entity.*;
import com.example.FirstSpring.Repository.EmployeeProjectRepository;
import com.example.FirstSpring.Repository.EmployeeRepository;
import com.example.FirstSpring.Repository.ProjectRepository;
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
    @Autowired
    EmployeeRepository employeeRepository;
    @Autowired
    ProjectRepository projectRepository;

    public List<EmployeeProject> findAllEmployeeProjects() {
        return employeeProjectRepository.findAll();
    }

    private EmployeeProjectDTO mapEmployeeProjectToEmployeeProjectDTO(EmployeeProject employeeProject) {
        EmployeeProjectDTO employeeProjectDTO = new EmployeeProjectDTO();
        employeeProjectDTO.setId(employeeProject.getId());
        employeeProjectDTO.setEmployee(employeeProject.getEmployee().getId());
        employeeProjectDTO.setProject(employeeProject.getProject().getId());
        employeeProjectDTO.setRole(employeeProject.getRole());
        employeeProjectDTO.setEfficiency(employeeProject.getEfficiency());
        return employeeProjectDTO;
    }

    public EmployeeProjectDTO findEmployeeProjectDTO(int id) throws Exception {
        if(!employeeProjectRepository.existsById(id)) {
            throw new Exception("findEmployeeProjectDTO(): Incorrect ID!");
        }
        return mapEmployeeProjectToEmployeeProjectDTO(employeeProjectRepository.findById(id).get());
    }

    public void saveEmployeeProject(EmployeeProjectDTO employeeProjectDTO) {
        EmployeeProject employeeProject = new EmployeeProject();
        employeeProject.setId(employeeProjectDTO.getId());
        employeeProject.setRole(employeeProjectDTO.getRole());
        employeeProject.setEfficiency(employeeProjectDTO.getEfficiency());
        if(employeeRepository.existsById(employeeProjectDTO.getEmployee())) {
            employeeProject.setEmployee(employeeRepository.getById(employeeProjectDTO.getEmployee()));
        }
        if(projectRepository.existsById(employeeProjectDTO.getProject())) {
            employeeProject.setProject(projectRepository.getById(employeeProjectDTO.getProject()));
        }
        employeeProjectRepository.save(employeeProject);
    }

    public void createEmployeeProject(EmployeeProject employeeProject) {
        employeeProjectRepository.save(employeeProject);
    }

    public Page<EmployeeProjectDTO> findEmployeeProjectsWithPagination(int offset, int pageSize) {
        Page<EmployeeProject> employeeProjects = employeeProjectRepository.findAll(PageRequest.of(offset,pageSize));
        return employeeProjects.map(this::mapEmployeeProjectToEmployeeProjectDTO);
    }

    public void deleteEmployeeProject(int id) throws Exception {
        if (!employeeProjectRepository.existsById(id)) {
            throw new Exception("deleteSpouse(): Incorrect ID!");
        }
        employeeProjectRepository.deleteById(id);
    }
}

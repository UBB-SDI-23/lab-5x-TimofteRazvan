package com.example.FirstSpring.Controller;

import com.example.FirstSpring.Entity.APIResponse;
import com.example.FirstSpring.Entity.Project;
import com.example.FirstSpring.Service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@ResponseBody
@CrossOrigin("http://localhost:3000")
public class ProjectController {
    @Autowired
    ProjectService projectService;

    @GetMapping("/projects")
    public List<Integer> findAllProjects() {
        List<Project> projectList = projectService.getAllProjects();
        ArrayList<Integer> idList = new ArrayList<>();
        for (Project project : projectList) {
            idList.add(project.getId());
        }
        return idList;
    }

    @GetMapping("/projects/page/{offset}/{pageSize}")
    private List<Project> getSpousesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
        Page<Project> projectList = projectService.findProjectsWithPagination(offset, pageSize);
        return projectList.getContent();
    }

    @GetMapping("/projects-details")
    public List<Project> findAllProjectsDetails() {
        return projectService.getAllProjects();
    }

    @GetMapping("/projects/{id}")
    public Project findProject(@PathVariable int id) {
        try {
            return projectService.getProject(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @PostMapping("/projects")
    public void createProject(@RequestBody Project project) {
        projectService.createProject(project);
    }

    @PutMapping("/projects/{id}")
    public void updateProject(@PathVariable int id, @RequestBody Project project) {
        try {
            projectService.updateProject(id, project);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @DeleteMapping("/projects/{id}")
    public void deleteProject(@PathVariable int id) {
        try {
            projectService.deleteProject(id);
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @GetMapping("/projects/maxPage")
    public Long getMaxPage(){
        return projectService.getProjectMaxPage();
    }
}

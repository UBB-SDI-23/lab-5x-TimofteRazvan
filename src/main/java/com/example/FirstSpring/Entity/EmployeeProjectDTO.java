package com.example.FirstSpring.Entity;

public class EmployeeProjectDTO {
    private int id;
    private int employee;
    private int project;
    private String role;
    private String efficiency;

    public EmployeeProjectDTO() {
    }

    public EmployeeProjectDTO(int id, int employee, int project, String role, String efficiency) {
        this.id = id;
        this.employee = employee;
        this.project = project;
        this.role = role;
        this.efficiency = efficiency;
    }

    public EmployeeProjectDTO(int employee, int project, String role, String efficiency) {
        this.employee = employee;
        this.project = project;
        this.role = role;
        this.efficiency = efficiency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployee() {
        return employee;
    }

    public void setEmployee(int employee) {
        this.employee = employee;
    }

    public int getProject() {
        return project;
    }

    public void setProject(int project) {
        this.project = project;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEfficiency() {
        return efficiency;
    }

    public void setEfficiency(String efficiency) {
        this.efficiency = efficiency;
    }
}

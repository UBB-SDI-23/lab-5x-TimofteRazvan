package com.example.FirstSpring.Repository;

import com.example.FirstSpring.Entity.EmployeeProject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeProjectRepository extends JpaRepository<EmployeeProject, Integer> {
    @Query(value="SELECT COUNT(*) from EmployeeProject")
    Long findNrEmployeeProjects();
}

package com.example.FirstSpring.Repository;

import com.example.FirstSpring.Entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// Uses in-memory database, so memory doesn't persist after closing the app (not stored on disk)
// Not recommended for production-based apps
// H2 is one such database
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    List<Employee> findByAgeGreaterThan(int age);
    Page<Employee> findByAgeGreaterThan(int age, Pageable pageable);
}

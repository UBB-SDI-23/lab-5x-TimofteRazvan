package com.example.FirstSpring.Repository;

import com.example.FirstSpring.Entity.Employee;
import com.example.FirstSpring.Entity.Spouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpouseRepository extends JpaRepository<Spouse, Integer> {
    List<Spouse> findByAgeGreaterThan(int age);

    @Query(value="SELECT COUNT(*) from Spouse")
    Long findNrSpouses();
}

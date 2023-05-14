package com.example.FirstSpring.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Entity(name = "User")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    private String username;
    @NotBlank
    private String password;
    private boolean activated;
/*
    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Employee> employees;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Address> addresses;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Spouse> spouses;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<Project> projects;

    @OneToMany(mappedBy = "user")
    @JsonIgnoreProperties("user")
    private List<EmployeeProject> employeeProjects;
 */

    @OneToOne(fetch = FetchType.LAZY,mappedBy="user")
    @JsonIgnoreProperties("user")
    private UserProfile userProfile;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}

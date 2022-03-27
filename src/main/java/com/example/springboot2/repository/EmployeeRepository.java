package com.example.springboot2.repository;

import com.example.springboot2.model.Company;
import com.example.springboot2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findByNameContaining(String name);

    List<Employee> findByCompanyId(Long id);

    List<Employee> findAllByOrderByNameAsc();

    List<Employee> findAllByOrderByNameDesc();
}



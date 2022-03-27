package com.example.springboot2.repository;

import com.example.springboot2.model.Company;
import com.example.springboot2.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByNameContaining(String name);

    List<Company> findAllByOrderByNameAsc();

    List<Company> findAllByOrderByNameDesc();
}

package com.empmanagement.repository;

import com.empmanagement.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByEmpNo(String empNo);

    List<Employee> findByIsActiveTrue();

    Page<Employee> findByIsActiveTrue(Pageable pageable);

    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.empNo) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.address) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
           "LOWER(e.email) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    Page<Employee> searchEmployees(@Param("keyword") String keyword, Pageable pageable);

    @Query("SELECT e.department, COUNT(e) FROM Employee e GROUP BY e.department")
    List<Object[]> countByDepartment();

    @Query("SELECT e.designation, COUNT(e) FROM Employee e GROUP BY e.designation")
    List<Object[]> countByDesignation();

    long countByIsActiveTrue();

    @Query("SELECT AVG(e.salary) FROM Employee e WHERE e.isActive = true")
    Double averageSalary();

    @Query("SELECT MAX(e.salary) FROM Employee e WHERE e.isActive = true")
    Double maxSalary();

    @Query("SELECT MIN(e.salary) FROM Employee e WHERE e.isActive = true")
    Double minSalary();
}

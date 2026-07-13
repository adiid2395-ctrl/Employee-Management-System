package com.empmanagement.service;

import com.empmanagement.model.Employee;
import com.empmanagement.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public Employee createEmployee(Employee employee) {
        if (employeeRepository.findByEmpNo(employee.getEmpNo()).isPresent()) {
            throw new RuntimeException("Employee number already exists");
        }
        return employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Page<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findByIsActiveTrue(pageable);
    }

    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        return employeeRepository.findByIsActiveTrue();
    }

    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Employee> getEmployeeByEmpNo(String empNo) {
        return employeeRepository.findByEmpNo(empNo);
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        Employee existing = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

        existing.setName(updatedEmployee.getName());
        existing.setSalary(updatedEmployee.getSalary());
        existing.setAddress(updatedEmployee.getAddress());
        existing.setEmail(updatedEmployee.getEmail());
        existing.setPhone(updatedEmployee.getPhone());
        existing.setDepartment(updatedEmployee.getDepartment());
        existing.setDesignation(updatedEmployee.getDesignation());
        existing.setDateOfJoining(updatedEmployee.getDateOfJoining());

        return employeeRepository.save(existing);
    }

    public void deleteEmployee(Long id) {
        Employee employee = employeeRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));
        employee.setIsActive(false);
        employeeRepository.save(employee);
    }

    @Transactional(readOnly = true)
    public Page<Employee> searchEmployees(String keyword, Pageable pageable) {
        return employeeRepository.searchEmployees(keyword, pageable);
    }

    @Transactional(readOnly = true)
    public Map<String, Object> getDashboardStats() {
        Map<String, Object> stats = new HashMap<>();

        stats.put("totalEmployees", employeeRepository.countByIsActiveTrue());
        stats.put("averageSalary", employeeRepository.averageSalary());
        stats.put("maxSalary", employeeRepository.maxSalary());
        stats.put("minSalary", employeeRepository.minSalary());

        Map<String, Long> deptStats = new HashMap<>();
        employeeRepository.countByDepartment().forEach(row -> {
            deptStats.put(row[0].toString(), (Long) row[1]);
        });
        stats.put("departmentStats", deptStats);

        Map<String, Long> desigStats = new HashMap<>();
        employeeRepository.countByDesignation().forEach(row -> {
            desigStats.put(row[0].toString(), (Long) row[1]);
        });
        stats.put("designationStats", desigStats);

        return stats;
    }
}

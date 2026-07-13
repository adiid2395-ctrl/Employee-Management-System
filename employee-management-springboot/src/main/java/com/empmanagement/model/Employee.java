package com.empmanagement.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Employee number is required")
    @Column(name = "emp_no", unique = true, nullable = false)
    private String empNo;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @NotNull(message = "Salary is required")
    @Min(value = 0, message = "Salary must be positive")
    @Column(nullable = false)
    private Double salary;

    @NotBlank(message = "Address is required")
    @Size(min = 5, max = 255, message = "Address must be between 5 and 255 characters")
    @Column(nullable = false)
    private String address;

    @Email(message = "Invalid email format")
    @Column(unique = true)
    private String email;

    @Pattern(regexp = "^\\+?[0-9\\s\\-]{10,}$", message = "Invalid phone number")
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Department department = Department.IT;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Designation designation = Designation.ASSOCIATE;

    @Column(name = "date_of_joining")
    private String dateOfJoining;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum Department {
        IT, HR, FINANCE, MARKETING, OPERATIONS, SALES, ADMIN
    }

    public enum Designation {
        INTERN, ASSOCIATE, SENIOR, LEAD, MANAGER, DIRECTOR, VP, CEO
    }
}

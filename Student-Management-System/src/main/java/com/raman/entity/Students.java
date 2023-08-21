package com.raman.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
@Table(name = "students")
@Service
public class Students {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "student_id", nullable = false)
    private int studentId;
    @Basic
    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;
    @Basic
    @Column(name = "last_name", nullable = true, length = 20)
    private String lastName;
    @Basic
    @Column(name = "date_of_Birth", nullable = true)
    private LocalDate dateOfBirth;
    @Basic
    @Column(name = "contact", nullable = true, length = 10)
    private String contact;
    @Basic
    @Column(name = "address", nullable = true, length = 50)
    private String address;
    @Basic
    @Column(name = "course", nullable = true, length = 10)
    private String course;
    @Basic
    @Column(name = "overall_grades", nullable = true, precision = 2)
    private Double overallGrades;

}

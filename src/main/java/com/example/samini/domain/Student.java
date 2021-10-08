package com.example.samini.domain;

import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    private double gpa;

    @Column(name = "birth_date")
    private LocalDate yearOfBirth;

    @Transient
    private int age;

    // A method that returns firstName and Lastname when an object of the class is logged
    @Override
    public String toString() {
        return "firstName: " + firstName + ", lastName: " + lastName + ", gpa: "+ gpa + ", age: "+age;
    }

    public Student(String lastName, String firstName, double gpa,LocalDate yearOfBirth) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.gpa = gpa;
        this.yearOfBirth = yearOfBirth;
    }

}

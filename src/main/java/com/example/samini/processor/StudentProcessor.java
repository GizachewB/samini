package com.example.samini.processor;

import com.example.samini.domain.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;


public class StudentProcessor implements ItemProcessor<Student, Student> {

    private static final Logger logger = LoggerFactory.getLogger(StudentProcessor.class);
    /**
     * This method transforms the input data from one form to another.
     */
    @Override
    public Student process(final Student student) throws Exception {
         String firstName = student.getFirstName().toUpperCase();
         String lastName = student.getLastName().toUpperCase();
         double gpa = student.getGpa();
         int age = student.getAge();
         int year = LocalDate.now().getYear() - age;
         LocalDate birthDate = LocalDate.of(year, 1, 1);
         student.setYearOfBirth(birthDate);

        final Student transformedStudent = new Student(firstName, lastName,gpa, student.getYearOfBirth());

        /**
        * logs the person entity to the application logs
        */
         logger.info("Converting (" + student + ") into (" + transformedStudent + ")");

        return student;
    }
}
package com.student.Repository;

import com.student.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

    Optional<Student> findById(int id);

    List<Student> findBySubject(String subject);

    List<Student> findAll();

}

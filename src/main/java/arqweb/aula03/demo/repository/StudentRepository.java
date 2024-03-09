package arqweb.aula03.demo.repository;

import arqweb.aula03.demo.model.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findAll();
    Student findById(Long id);
    Student save(Student student);
    Student delete(Long id);

    Student update(Long id, Student student);
}
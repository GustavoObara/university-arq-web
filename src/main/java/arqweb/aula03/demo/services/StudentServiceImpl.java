package arqweb.aula03.demo.services;

import arqweb.aula03.demo.model.Student;
import arqweb.aula03.demo.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    @Override
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student deleteStudentById(Long id) {
        return studentRepository.delete(id);
    }

    @Override
    public Student updateStudentById(Long id, Student student) {
        return studentRepository.update(id, student);
    }
}
package arqweb.aula03.demo.repository;

import org.springframework.stereotype.Repository;
import arqweb.aula03.demo.model.Student;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final List<Student> students = new ArrayList<>();
    private Long nextId;

    public StudentRepositoryImpl() {
        students.add(new Student(1L, "Luiz Obara"    , "111.111.111-11", "TEC-ADS" , 21));
        students.add(new Student(2L, "Júlia Almeida" , "222.222.222-22", "ARQ-URB" , 20));
        students.add(new Student(3L, "Felipe Lima"   , "333.333.333-33", "TEC-ADS" , 20));
        students.add(new Student(4L, "Tiago Bernardo", "444.444.444-44", "ENG-PROD", 22));
        students.add(new Student(5L, "Pedro Mattos"  , "555.555.555-55", "TEC-JGD" , 21));
        nextId = 6L;
    }

    @Override
    public List<Student> findAll() {
        return students;
    }

    @Override
    public Student findById(Long id) {
        return students.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            student.setId(nextId++);
            students.add(student);
        } else {
            students.removeIf(t -> t.getId().equals(student.getId()));
            students.add(student);
        }
        return student;
    }

    @Override
    public Student delete(Long id){
        Student deletedStudent = this.findById(id);
        if (deletedStudent != null) {
            students.removeIf(student -> student.getId().equals(id));
        }
        return deletedStudent;
    }

    @Override
    public Student update(Long id, Student student){
        Student updateStudent = this.findById(id);
        if (updateStudent != null) {
            students.set(students.indexOf(updateStudent), student);
        }
        return student;
    }
}
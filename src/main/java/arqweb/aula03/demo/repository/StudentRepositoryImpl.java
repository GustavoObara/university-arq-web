package arqweb.aula03.demo.repository;

import org.springframework.stereotype.Repository;
import arqweb.aula03.demo.model.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

@Repository
public class StudentRepositoryImpl implements StudentRepository {
    private final JdbcTemplate jdbcTemplate;

    public StudentRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAll() {
        return jdbcTemplate.query("SELECT * FROM student", (resultSet, rowNum) ->
            new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    Student.formatCpf(resultSet.getString("document")),
                    resultSet.getString("course"),
                    resultSet.getInt("age")
            )
        );
    }

    @Override
    public Student findById(Long id) {
        String query = "SELECT * FROM student WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, (resultSet,  rowNum) ->
            new Student(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    Student.formatCpf(resultSet.getString("document")),
                    resultSet.getString("course"),
                    resultSet.getInt("age")
            )
        );
    }

    @Override
    public Student save(Long id, Student student) {
        if (id == null) {
            String insertQuery = "INSERT INTO public.student (name, document, course, age) VALUES (?, ?, ?, ?)";
            jdbcTemplate.update(insertQuery, student.getName(), student.getDocument(), student.getCourse(), student.getAge());
        } else {
            // Sem troca de ID
            if (this.findById(id) != null) {
                String updateQuery = "UPDATE public.student SET name = ?, document = ?, course = ?, age = ? WHERE id = ?";
                jdbcTemplate.update(updateQuery, student.getName(), student.getDocument(), student.getCourse(), student.getAge(), student.getId());
            } else {
                throw new IllegalArgumentException("Não existe nenhum estudante com esse registro.");
            }
        }
        return student;
    }

    @Override
    public Student delete(Long id){
        Student student = findById(id);
        String deleteQuery = "DELETE FROM public.student WHERE id = ?";
        jdbcTemplate.update(deleteQuery, new Object[]{id});
        return student;
    }
}
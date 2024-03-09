package arqweb.aula03.demo.model;

public class Student {
    private Long id;
    private String name;
    private String document;
    private String course;
    private int age;

    public Student(Long id, String name, String document, String course, int age) {
        this.id = id;
        this.name = name;
        this.document = document;
        this.course = course;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
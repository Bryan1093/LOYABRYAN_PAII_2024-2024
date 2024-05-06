package Clases;

public class Student {
    // Atributos de la clase Student
    private int id;          // Identificador del estudiante
    private String name;     // Nombre del estudiante
    private String lastname; // Apellido del estudiante
    private int age;         // Edad del estudiante

    // Constructor vacío
    public Student() {
        // Constructor vacío necesario para algunas operaciones
    }

    // Constructor con todos los atributos
    public Student(int id, String name, String lastname, int age) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
    }

    // Getters y setters para los atributos de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Método toString para representar el objeto como cadena de texto
    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", lastname=" + lastname + ", age=" + age + "]";
    }
}
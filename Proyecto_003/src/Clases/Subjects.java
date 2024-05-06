package Clases;

public class Subjects {
    // Atributos de la clase Subjects
    private int id;           // Identificador de la asignatura
    private String name;      // Nombre de la asignatura
    private String description; // Descripción de la asignatura
    private String level;     // Nivel de la asignatura

    // Constructor vacío
    public Subjects() {
        // Constructor vacío necesario para algunas operaciones
    }

    // Constructor con todos los atributos
    public Subjects(int id, String name, String description, String level) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.level = level;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    // Método toString para representar el objeto como cadena de texto
    @Override
    public String toString() {
        return "Subjects [id=" + id + ", name=" + name + ", description=" + description + ", level=" + level + "]";
    }
}
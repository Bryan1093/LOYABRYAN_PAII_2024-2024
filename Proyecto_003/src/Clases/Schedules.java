package Clases;

import java.sql.Time;

public class Schedules {
    // Atributos de la clase Schedules
    private int id;          // Identificador del horario
    private int id_sub;      // Identificador de la asignatura asociada al horario
    private int id_stu;      // Identificador del estudiante asociado al horario
    private int id_tea;      // Identificador del profesor asociado al horario
    private Time start_time; // Hora de inicio del horario
    private Time end_time;   // Hora de fin del horario
    private String day;      // Día en que se realiza el horario

    // Constructor vacío
    public Schedules() {
        // Constructor vacío necesario para algunas operaciones
    }

    // Constructor con todos los atributos
    public Schedules(int id, int id_sub, int id_stu, int id_tea, Time start_time, Time end_time, String day) {
        this.id = id;
        this.id_sub = id_sub;
        this.id_stu = id_stu;
        this.id_tea = id_tea;
        this.start_time = start_time;
        this.end_time = end_time;
        this.day = day;
    }

    // Getters y setters para los atributos de la clase
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_sub() {
        return id_sub;
    }

    public void setId_sub(int id_sub) {
        this.id_sub = id_sub;
    }

    public int getId_stu() {
        return id_stu;
    }

    public void setId_stu(int id_stu) {
        this.id_stu = id_stu;
    }

    public int getId_tea() {
        return id_tea;
    }

    public void setId_tea(int id_tea) {
        this.id_tea = id_tea;
    }

    public Time getStart_time() {
        return start_time;
    }

    public void setStart_time(Time start_time) {
        this.start_time = start_time;
    }

    public Time getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Time end_time) {
        this.end_time = end_time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    // Método toString para representar el objeto como cadena de texto
    @Override
    public String toString() {
        return "Schedules [id=" + id + ", id_sub=" + id_sub + ", id_stu=" + id_stu + ", id_tea=" + id_tea
                + ", start_time=" + start_time + ", end_time=" + end_time + ", day=" + day + "]";
    }
}
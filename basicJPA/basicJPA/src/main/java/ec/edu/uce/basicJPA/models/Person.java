/*package ec.edu.uce.basicJPA.models;

import ec.edu.uce.basicJPA.repository.PersonRepository;
import jakarta.persistence.*;

@Entity
@Table(name = "person")
public class Person {
    @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column
    private String name;
    @Column
    private String lastname;
    @Column
    private int age;


    public Person() {
    }

    public Person(String name, String lastname, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.lastname = lastname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
        this.lastname =lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + name + '\'' +
                ", lastName='" + lastname + '\'' +
                ", age=" + age +
                '}';
    }
}*/
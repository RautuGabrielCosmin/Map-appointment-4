package domain;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.Serializable;
import java.util.Objects;

public class Dentist
        implements Identifiable<Integer>, Serializable {


    //private attributes

    @JacksonXmlProperty(localName = "Id")
    private int id;

    @JacksonXmlProperty(localName = "Name")
    private String name;

    @JacksonXmlProperty(localName = "Age")
    private int age;


    //constructors
    public Dentist() {}

    public Dentist(int id, String name, int age) {

        this.id = id;

        this.name = name;

        this.age = age;
    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }


    @Override
    public String toString() {
        return "Dentist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) return true;
        if (otherObject == null || getClass() != otherObject.getClass()) return false;
        Dentist dentist = (Dentist) otherObject;
        return id == dentist.id && age == dentist.age && Objects.equals(name, dentist.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, age);
    }

    @Override
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }


}

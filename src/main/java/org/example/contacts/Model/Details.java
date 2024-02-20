package org.example.contacts.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "contact")
public class Details {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private int id;
    private String name;
    private String mobileno;
    private String address;


    public Details() {

    }

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

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", address='" + address + '\'' +
                '}';
    }


}

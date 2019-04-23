package com.xairlab.otus.hibernate.entity;

import javax.persistence.*;

@Entity
public class AddressDataSet {

    @Id
    @GeneratedValue
    private long id;

    private String street;

    @OneToOne
    private User user;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return "AddressDataSet{" +
                "id=" + id +
                ", street='" + street + '\'' +
                ", user=" + user +
                '}';
    }
}

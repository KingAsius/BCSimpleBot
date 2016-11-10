package com.simplebot.entity;

import javax.persistence.*;

/**
 * Created by Vladislav on 11/3/2016.
 */
@Entity
@Table(name = "info")
public class Info {

    @Id
    @GeneratedValue
    private Integer id;

    @Column
    private Integer hours;

    @Column
    private String description;

    @ManyToOne
    private User user;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Info() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getHours() {
        return hours;
    }
}

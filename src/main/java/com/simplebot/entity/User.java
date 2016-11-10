package com.simplebot.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.simplebot.model.enums.Gender;

import javax.persistence.*;

/**
 * Created by Vladislav on 11/10/2016.
 */
@Entity
@Table(name = "user")
public class User {
    @Id
    private Long id;

    @Column
    @JsonProperty("first_name")
    private String firstName;

    @Column
    @JsonProperty("last_name")
    private String lastName;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "timezone")
    private Integer timezone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Integer getTimezone() {
        return timezone;
    }

    public void setTimezone(Integer timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", timezone=" + timezone +
                '}';
    }
}
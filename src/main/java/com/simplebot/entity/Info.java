package com.simplebot.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Vladislav on 11/3/2016.
 */
@Entity
@Table(name = "info")
public class Info {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "message")
    private String message;

    @Column(name = "hours")
    private int hours;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}

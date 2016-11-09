package com.simplebot.entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

/**
 * Created by Vladislav on 11/3/2016.
 */
@Entity
@Table(name = "info")
public class Info {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "userId")
    private long usedId;

    @Column
    private Date date = Date.valueOf(LocalDate.now());

    @Column(name = "hours")
    private int hours = -1;

    private String text;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUsedId() {
        return usedId;
    }

    public void setUsedId(long usedId) {
        this.usedId = usedId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Info(long usedId, int hours) {
        this.usedId = usedId;
        this.hours = hours;
    }

    public Info() {
    }
}

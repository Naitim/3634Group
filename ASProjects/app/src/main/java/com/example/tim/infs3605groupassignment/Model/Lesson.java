package com.example.tim.infs3605groupassignment.Model;

/**
 * Created by Tim on 21-Oct-16.
 */
public class Lesson {
    private int id;
    private String name, date, topic, course;

    public Lesson() {
    }

    public Lesson(int id) {
        this.id = id;
    }

    public Lesson(String name, String date, String topic, String course) {
        this.name = name;
        this.date = date;
        this.topic = topic;
        this.course = course;
    }

    public Lesson(int id, String name, String date, String topic, String course) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.topic = topic;
        this.course = course;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }
}

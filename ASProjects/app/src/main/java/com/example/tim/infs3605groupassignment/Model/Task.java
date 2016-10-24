package com.example.tim.infs3605groupassignment.Model;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.tim.infs3605groupassignment.R;

/**
 * Created by Tim on 23-Oct-16.
 */
public class Task {
    private String name, time, description, requirements, priority, studentsCompleted;
    private int id, lesson_id;

    public Task() {
    }

    public Task(String name, String time, String description, String requirements, String priority, String studentsCompleted) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.requirements = requirements;
        this.priority = priority;
        this.studentsCompleted = studentsCompleted;
    }

    public Task(String name, String time, String description, String requirements, String priority, String studentsCompleted, int lesson_id) {
        this.name = name;
        this.time = time;
        this.description = description;
        this.requirements = requirements;
        this.priority = priority;
        this.studentsCompleted = studentsCompleted;
        this.lesson_id = lesson_id;
    }

    public Task(int id, int lesson_id, String name, String time, String description, String requirements, String priority, String studentsCompleted) {
        this.id = id;
        this.lesson_id = lesson_id;
        this.name = name;
        this.time = time;
        this.description = description;
        this.requirements = requirements;
        this.priority = priority;
        this.studentsCompleted = studentsCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirements() {
        return requirements;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStudentsCompleted() {
        return studentsCompleted;
    }

    public void setStudentsCompleted(String studentsCompleted) {
        this.studentsCompleted = studentsCompleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLesson_id() {
        return lesson_id;
    }

    public void setLesson_id(int lesson_id) {
        this.lesson_id = lesson_id;
    }

    public static class TaskCreator extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_task_creator);

            final SQLiteDatabase mydatabase = openOrCreateDatabase("Tutorial_Planner",MODE_PRIVATE,null);






        }
    }
}

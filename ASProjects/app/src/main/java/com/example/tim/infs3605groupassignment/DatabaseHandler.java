package com.example.tim.infs3605groupassignment;

import com.example.tim.infs3605groupassignment.Model.Lesson;
import com.example.tim.infs3605groupassignment.Model.Task;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Tim on 23-Oct-16.
 * Tutorial and code modified from Androidhive.info
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Tutorial_Planner";

    // Table name
    private static final String TABLE_LESSON = "Lessons";
    private static final String TABLE_TASKS = "Tasks";


    // Lesson Table Columns names
    private static final String KEY_LESSON_ID = "lesson_id";
    private static final String KEY_lESSON_NAME = "lesson_Name";
    private static final String KEY_TOPIC = "topic_Name";
    private static final String KEY_COURSE = "course_Name";
    private static final String KEY_DATE = "date";

    // Task Table Columns name
    private static final String KEY_TASK_ID = "task_id";
    private static final String KEY_TASK_NAME = "task_Name" ;
    private static final String KEY_TIME = "time" ;
    private static final String KEY_DESCRIPTION = "desc" ;
    private static final String KEY_REQUIREMENTS = "req" ;
    private static final String KEY_PRIORITY = "priority" ;
    private static final String KEY_STUDENTS_COMPLETED = "stuComp" ;
    private static final String KEY_LESSON_FK = "lesson_id";

    // Table Create Statements
    private static final String CREATE_LESSONS_TABLE = "CREATE TABLE "
            + TABLE_LESSON + "("
            + KEY_LESSON_ID + " INTEGER PRIMARY KEY,"
            + KEY_lESSON_NAME + " TEXT,"
            + KEY_DATE + " TEXT,"
            + KEY_TOPIC + " TEXT,"
            + KEY_COURSE + " TEXT " +
            ")";

    private static final String CREATE_TASKS_TABLE = "CREATE TABLE "
            + TABLE_TASKS + "("
            + KEY_TASK_ID + " INTEGER PRIMARY KEY,"
            + KEY_TASK_NAME + " TEXT,"
            + KEY_TIME + " TEXT,"
            + KEY_DESCRIPTION + " TEXT,"
            + KEY_REQUIREMENTS + " TEXT,"
            + KEY_PRIORITY + " TEXT,"
            + KEY_STUDENTS_COMPLETED + " TEXT,"
            + KEY_LESSON_FK + " TEXT,"
            + " FOREIGN KEY("+KEY_LESSON_FK+") REFERENCES "+TABLE_LESSON+"("+KEY_LESSON_ID +
            "));";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_LESSONS_TABLE);
        db.execSQL(CREATE_TASKS_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TASKS);
        // Create tables again
        onCreate(db);
    }

    // Adding new Lesson
    public void addLesson(Lesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_lESSON_NAME, lesson.getName()); // Lesson name'
        values.put(KEY_DATE, lesson.getCourse());
        values.put(KEY_TOPIC, lesson.getDate());
        values.put(KEY_COURSE, lesson.getTopic());

        //System.out.println("Name: "+lesson.getName()+" Date: "+lesson.getDate()+" Topic: "+lesson.getTopic()+" Course: " +lesson.getCourse());
        db.insert(TABLE_LESSON, null, values);
        db.close();
    }

    // Getting single Lesson
    public Lesson getLesson(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_LESSON, new String[]{KEY_LESSON_ID, KEY_lESSON_NAME, KEY_TOPIC, KEY_COURSE, KEY_DATE}, KEY_LESSON_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null); // check if null amounts are correct
        if (cursor != null)
            cursor.moveToFirst();

        Lesson lesson = new Lesson(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));

        return lesson;
    }

    // Getting All Lessons
    public List<Lesson> getAllLessons() {
        List<Lesson> lessonList = new ArrayList<Lesson>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_LESSON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Lesson lesson = new Lesson();
                lesson.setName(cursor.getString(1));
                lesson.setDate(cursor.getString(2));
                lesson.setTopic(cursor.getString(3));
                lesson.setCourse(cursor.getString(4));
                // Adding lesson to list
                lessonList.add(lesson);
            } while (cursor.moveToNext());
        }

        // return contact list
        return lessonList;
    }


    // Getting Lessons Count
    public int getLessonsCount() {
        int count = 0;
        String countQuery = "SELECT * FROM " + TABLE_LESSON;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        if(cursor != null && !cursor.isClosed()){
            count = cursor.getCount();
            cursor.close();
        }

        // return count
        return count;
    }

    // Updating single Lesson
    public int updateLesson(Lesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_lESSON_NAME, lesson.getName());
        values.put(KEY_TOPIC, lesson.getTopic());
        values.put(KEY_COURSE, lesson.getCourse());
        values.put(KEY_DATE, lesson.getDate());

        // updating row
        return db.update(TABLE_LESSON, values, KEY_LESSON_ID + " = ?",
                new String[] { String.valueOf(lesson.getId()) });
    }

    // Deleting single Lesson
    public void deleteLesson(Lesson lesson) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LESSON, KEY_LESSON_ID + " = ?",
                new String[] { String.valueOf(lesson.getId()) });
        db.close();
    }

    // Adding new Task
    public void addTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TASK_NAME, task.getName()); // Task name
        values.put(KEY_TIME, task.getTime());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_REQUIREMENTS, task.getRequirements());
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_STUDENTS_COMPLETED, task.getStudentsCompleted());
        values.put(KEY_LESSON_FK, task.getLesson_id());

        db.insert(TABLE_TASKS, null, values);
        db.close();
    }

    // Getting single Task
    public Task getTask(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TASKS, new String[]{KEY_TASK_ID, KEY_lESSON_NAME, KEY_TOPIC, KEY_COURSE, KEY_DATE, KEY_LESSON_FK}, KEY_TASK_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null); // check if null amounts are correct
        if (cursor != null)
            cursor.moveToFirst();

        Task task = new Task( cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), cursor.getInt(7));

        return task;
    }

    // Getting All Tasks
    public List<Task> getAllTasks() {
        List<Task> tasksList = new ArrayList<Task>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_TASKS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Task task = new Task();
                task.setName(cursor.getString(1));
                task.setTime(cursor.getString(2));
                task.setDescription(cursor.getString(3));
                task.setRequirements(cursor.getString(4));
                task.setPriority(cursor.getString(5));
                task.setStudentsCompleted(cursor.getString(6));
                task.setLesson_id(7);
                // Adding task to list
                tasksList.add(task);
            } while (cursor.moveToNext());
        }

        // return contact list
        return tasksList;
    }


    // Getting Tasks Count
    public int getTasksCount() {
        String countQuery = "SELECT * FROM " + TABLE_TASKS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single Task
    public int updateTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_lESSON_NAME, task.getName());
        values.put(KEY_TIME, task.getTime());
        values.put(KEY_DESCRIPTION, task.getDescription());
        values.put(KEY_REQUIREMENTS, task.getRequirements());
        values.put(KEY_PRIORITY, task.getPriority());
        values.put(KEY_STUDENTS_COMPLETED, task.getStudentsCompleted());
        values.put(KEY_LESSON_FK, task.getLesson_id());

        // updating row
        return db.update(TABLE_TASKS, values, KEY_TASK_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
    }

    // Deleting single Task
    public void deleteTask(Task task) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_TASKS, KEY_TASK_ID + " = ?",
                new String[] { String.valueOf(task.getId()) });
        db.close();
    }


    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
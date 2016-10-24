package com.example.tim.infs3605groupassignment.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tim.infs3605groupassignment.DatabaseHandler;
import com.example.tim.infs3605groupassignment.Model.Lesson;
import com.example.tim.infs3605groupassignment.R;
import com.example.tim.infs3605groupassignment.View.MainActivity;

public class LessonCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_creator);

//        final SQLiteDatabase mydatabase = openOrCreateDatabase("Tutorial_Planner",MODE_PRIVATE,null);
//        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS Lessons(LessonName VARCHAR,TopicName VARCHAR,CourseName VARCHAR,Date VARCHAR);");
//
//        //TESTING WHETHER DATA HAS STORED PROPERLY
//        Cursor resultSet = mydatabase.rawQuery("Select * from Lessons",null);
//        if(resultSet.moveToFirst()) {
//            System.out.println(resultSet.getString(0)+resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3));
//        }

        if (getIntent().hasExtra("id")) {
            Bundle extras = getIntent().getExtras();
            final String name = extras.getString("name");
            final String date2 = extras.getString("date");
            final String topic = extras.getString("topic");
            final String course = extras.getString("course");
            //System.out.println(extras.getString("id"));
//            String stringID = extras.getString("id");
//            int id = Integer.parseInt(stringID);
            int id = extras.getInt("id");
            System.out.println(id);

            TextView lessonName = (TextView) findViewById(R.id.editLessonName);
            TextView topicName = (TextView) findViewById(R.id.editTopic);
            TextView courseName = (TextView) findViewById(R.id.editCourse);
            TextView date = (TextView) findViewById(R.id.editDate);


            lessonName.setText(name);
            date.setText(date2);
            topicName.setText(topic);
            courseName.setText(course);
            DatabaseHandler db = new DatabaseHandler(this);
            Lesson lesson = new Lesson(id, name, date2, topic, course);
            db.deleteLesson(lesson);
            if(getIntent().hasExtra("check")) {
                if (extras.getBoolean("check") == true) {
                    Intent i = new Intent(this, MainActivity.class);
                    this.startActivity(i);
                }
            }
        }


        final Button button = (Button) findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Action


                //Grabbing input data
                TextView lessonName = (TextView) findViewById(R.id.editLessonName);
                TextView topicName = (TextView) findViewById(R.id.editTopic);
                TextView courseName = (TextView) findViewById(R.id.editCourse);
                TextView date = (TextView) findViewById(R.id.editDate);


                String lessonNameToEnter = lessonName.getText().toString();
                String topic = topicName.getText().toString();
                String course = courseName.getText().toString();
                String dateString = date.getText().toString();

                //System.out.println("Name: "+lessonNameToEnter+" Date: "+dateString+" Topic: "+topic+" Course: " +course);

                Lesson lessonToCreate = new Lesson(lessonNameToEnter, dateString, topic, course);

                //System.out.println("Name: "+lessonToCreate.getName()+" Date: "+lessonToCreate.getDate()+" Topic: "+lessonToCreate.getTopic()+" Course: " +lessonToCreate.getCourse());

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                db.addLesson(lessonToCreate);


//
//                //Storing input data into SQLite database
//                mydatabase.execSQL("INSERT INTO Lessons VALUES('" +lessonNameToEnter +"','" +topic+ "','" +course+ "','" +dateString+"');");

                /*Cursor resultSet = mydatabase.rawQuery("Select * from Lessons",null);
                resultSet.moveToFirst();
                System.out.println(resultSet.getString(1)+resultSet.getString(2)+resultSet.getString(3)+resultSet.getString(4));*/


                Context context = getApplicationContext();
                CharSequence text = "Submitted";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();

                //Return to main screen on success

                Intent i = new Intent(context, MainActivity.class);
                startActivity(i);

            }
        });
    }


}

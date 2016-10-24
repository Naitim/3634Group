package com.example.tim.infs3605groupassignment.View;

import com.example.tim.infs3605groupassignment.DatabaseHandler;
import com.example.tim.infs3605groupassignment.Model.DividerDecoration;
import com.example.tim.infs3605groupassignment.Controller.LessonCreator;
import com.example.tim.infs3605groupassignment.Controller.LessonsAdapter;
import com.example.tim.infs3605groupassignment.Model.Lesson;
import com.example.tim.infs3605groupassignment.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Lesson> lessonList = new ArrayList<>();
    private RecyclerView recyclerView;
    private LessonsAdapter mAdapter;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerDecoration(this));

        mAdapter = new LessonsAdapter(lessonList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

//        if (getIntent().hasExtra("check")) {
//            Bundle extras = getIntent().getExtras();
//            Boolean check = extras.getBoolean("check");
//            if(check == true){
//                int id = extras.getInt("id");
//                System.out.println("Delete: "+id);
//                Lesson lesson = new Lesson(id-1);
//                db.deleteLesson(lesson);
//            }
//        }
        //Lesson testLesson = new Lesson("nameTest", "dateTest", "topicTest", "courseTest");
        //db.addLesson(testLesson);
        addLessonData();
    }

    @Override
    public void onResume() {
        super.onResume();
        addLessonData();
    }


    private void addLessonData() {
        int count = 0;
        count = db.getLessonsCount();

        //System.out.println(count);

        lessonList.clear();

        for (int i = 1; i <= count; i++) {
            Lesson lesson = db.getLesson(i);
            System.out.println(lesson.getId());
            lessonList.add(lesson);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void swapScreens(View view) {
        Intent i = new Intent(this.getApplicationContext(), LessonCreator.class);
        startActivity(i);
    }


}

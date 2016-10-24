package com.example.tim.infs3605groupassignment.View;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tim.infs3605groupassignment.DatabaseHandler;
import com.example.tim.infs3605groupassignment.Model.DividerDecoration;
import com.example.tim.infs3605groupassignment.Model.Task;
import com.example.tim.infs3605groupassignment.R;
import com.example.tim.infs3605groupassignment.Controller.TaskCreator;
import com.example.tim.infs3605groupassignment.Controller.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tim on 24-Oct-16.
 */
public class TaskMain extends AppCompatActivity {
    private List<Task> taskList = new ArrayList<>();
    private RecyclerView recyclerView;
    private TasksAdapter mAdapter;
    DatabaseHandler db = new DatabaseHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.addItemDecoration(new DividerDecoration(this));

        mAdapter = new TasksAdapter(taskList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        addTaskData();
    }

    @Override
    public void onResume() {
        super.onResume();
        addTaskData();
    }


    private void addTaskData() {
        int count = 0;
        count = db.getLessonsCount();

        //System.out.println(count);

        taskList.clear();

        for (int i = 1; i <= count; i++) {
            Task task = db.getTask(i);
            taskList.add(task);
        }
        mAdapter.notifyDataSetChanged();
    }

    public void swapScreens(View view) {
        Intent i = new Intent(this.getApplicationContext(), TaskCreator.class);
        startActivity(i);
    }

}
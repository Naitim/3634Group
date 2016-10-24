package com.example.tim.infs3605groupassignment.Controller;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tim.infs3605groupassignment.DatabaseHandler;
import com.example.tim.infs3605groupassignment.Model.Task;
import com.example.tim.infs3605groupassignment.R;

public class TaskCreator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_creator);

        final Button button = (Button) findViewById(R.id.btnSubmit);
        button.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //Action

                //Grabbing input data
                TextView taskName = (TextView)findViewById(R.id.editName);
                String st = taskName.getText().toString();
                TextView taskTime = (TextView)findViewById(R.id.editTime);
                String time = taskTime.getText().toString();
                TextView taskDesc = (TextView)findViewById(R.id.editDesc);
                String description = taskDesc.getText().toString();
                TextView reqs = (TextView)findViewById(R.id.editReqs);
                String requirements = reqs.getText().toString();
                TextView priority = (TextView)findViewById(R.id.editPriority);
                String priorityStr = priority.getText().toString();
                TextView studentsComp = (TextView)findViewById(R.id.editCompleted);
                String completed = studentsComp.getText().toString();

                Task taskToCreate = new Task(st, time, description, requirements, priorityStr, completed);

                DatabaseHandler db = new DatabaseHandler(getApplicationContext());

                db.addTask(taskToCreate);

                Context context = getApplicationContext();
                CharSequence text = "Submitted";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }
}
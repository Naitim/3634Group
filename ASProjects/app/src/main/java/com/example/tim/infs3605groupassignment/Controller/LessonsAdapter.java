package com.example.tim.infs3605groupassignment.Controller;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.tim.infs3605groupassignment.DatabaseHandler;
import com.example.tim.infs3605groupassignment.Model.Lesson;
import com.example.tim.infs3605groupassignment.R;
import com.example.tim.infs3605groupassignment.View.MainActivity;

import java.util.List;

/**
 * Created by Tim on 21-Oct-16.
 * Tutorial and code modified from Androidhive.info
 */
public class LessonsAdapter extends RecyclerView.Adapter<LessonsAdapter.MyViewHolder> {

    private List<Lesson> lessonList;

    private Context mContext;

    private DatabaseHandler db;



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, topic, course;
        public Button editBtn, deleteBtn, taskBtn;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.titleTextView);
            date = (TextView) view.findViewById(R.id.dateTextView);
            topic = (TextView) view.findViewById(R.id.topicTextView);
            course = (TextView) view.findViewById(R.id.courseTextView);
            editBtn = (Button) view.findViewById(R.id.editButton);
            deleteBtn = (Button) view.findViewById(R.id.deleteButton);
            taskBtn = (Button) view.findViewById(R.id.tasksButton);
        }
    }

    public LessonsAdapter(List<Lesson> lessonList) {
        this.lessonList = lessonList;
    }

    public LessonsAdapter(Context context, List<Lesson> lessonList) {
        this.lessonList = lessonList;
        mContext = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.lesson_list_row, parent, false);

        return new MyViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        final Lesson lesson = lessonList.get(position);

        holder.title.setText(lesson.getName());
        holder.date.setText(lesson.getDate());
        holder.topic.setText(lesson.getTopic());
        holder.course.setText(lesson.getCourse());
        holder.editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), LessonCreator.class);
                i.putExtra("id", lesson.getId());
                i.putExtra("name", lesson.getName());
                i.putExtra("date", lesson.getDate());
                i.putExtra("topic", lesson.getTopic());
                i.putExtra("course", lesson.getCourse());
                v.getContext().startActivity(i);
            }
        });

        holder.taskBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), TasksAdapter.class);
                System.out.println(lesson.getId());
                i.putExtra("id", lesson.getId());
                v.getContext().startActivity(i);
            }
        });
        holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(v.getContext(), LessonCreator.class);
            i.putExtra("check", true);
            i.putExtra("id", lesson.getId());
            i.putExtra("name", lesson.getName());
            i.putExtra("date", lesson.getDate());
            i.putExtra("topic", lesson.getTopic());
            i.putExtra("course", lesson.getCourse());
            v.getContext().startActivity(i);
        }
    });
    }

    @Override
    public int getItemCount(){
        return lessonList.size();
    }


}




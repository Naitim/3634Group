package com.example.tim.infs3605groupassignment.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tim.infs3605groupassignment.Model.Task;
import com.example.tim.infs3605groupassignment.R;

import java.util.List;

/**
 * Created by Tim on 24-Oct-16.
 */
public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {

    private List<Task> taskList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, time, priority;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nameTextView);
            time = (TextView) view.findViewById(R.id.timeTextView);
            priority = (TextView) view.findViewById(R.id.priorityTextView);
        }
    }


    public TasksAdapter(List<Task> taskListList) {
        this.taskList = taskListList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.name.setText(task.getName());
        holder.time.setText(task.getTime());
        holder.priority.setText(task.getPriority());
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
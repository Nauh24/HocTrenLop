package com.nauh.demo.kiemtra3mau.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.nauh.demo.R;
import com.nauh.demo.kiemtra3mau.model.Course;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder> {
    private Context context;
    private List<Course> courseList;
    private OnCourseClickListener listener;
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public interface OnCourseClickListener {
        void onCourseClick(Course course);
    }

    public CourseAdapter(Context context, List<Course> courseList, OnCourseClickListener listener) {
        this.context = context;
        this.courseList = courseList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_course, parent, false);
        return new CourseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = courseList.get(position);
        
        holder.tvId.setText(String.valueOf(course.getId()));
        holder.tvName.setText(course.getName());
        holder.tvStartDate.setText(dateFormat.format(course.getStartDate()));
        holder.tvEndDate.setText(dateFormat.format(course.getEndDate()));
        
        holder.itemView.setOnClickListener(v -> {
            if (listener != null) {
                listener.onCourseClick(course);
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

    public void updateData(List<Course> newCourseList) {
        this.courseList = newCourseList;
        notifyDataSetChanged();
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        TextView tvId, tvName, tvStartDate, tvEndDate;

        public CourseViewHolder(@NonNull View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tv_id);
            tvName = itemView.findViewById(R.id.tv_name);
            tvStartDate = itemView.findViewById(R.id.tv_start_date);
            tvEndDate = itemView.findViewById(R.id.tv_end_date);
        }
    }
}

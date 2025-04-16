package com.nauh.demo.kiemtra3mau.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.nauh.demo.R;
import com.nauh.demo.kiemtra3mau.adapter.CourseAdapter;
import com.nauh.demo.kiemtra3mau.database.DatabaseHelper;
import com.nauh.demo.kiemtra3mau.model.Course;

import java.util.List;

public class CourseFragment extends Fragment implements CourseAdapter.OnCourseClickListener {
    private RecyclerView recyclerView;
    private CourseAdapter courseAdapter;
    private DatabaseHelper databaseHelper;
    private EditText etSearch;
    private List<Course> courseList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_course, container, false);
        
        databaseHelper = new DatabaseHelper(getContext());
        
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        etSearch = view.findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                searchCourses(s.toString());
            }
        });
        
        loadCourses();
        
        return view;
    }

    private void loadCourses() {
        courseList = databaseHelper.getAllCourses();
        courseAdapter = new CourseAdapter(getContext(), courseList, this);
        recyclerView.setAdapter(courseAdapter);
    }

    private void searchCourses(String keyword) {
        List<Course> filteredList = databaseHelper.searchCoursesByName(keyword);
        courseAdapter.updateData(filteredList);
    }

    @Override
    public void onCourseClick(Course course) {
        // Handle course click if needed
    }
}

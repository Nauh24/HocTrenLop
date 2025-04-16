package com.nauh.demo.kiemtra3mau.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.nauh.demo.R;
import com.nauh.demo.kiemtra3mau.adapter.StudentAdapter;
import com.nauh.demo.kiemtra3mau.database.DatabaseHelper;
import com.nauh.demo.kiemtra3mau.model.Course;
import com.nauh.demo.kiemtra3mau.model.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StatisticsFragment extends Fragment {
    private Spinner spinnerCourses;
    private CheckBox checkBoxActive;
    private RecyclerView recyclerView;
    private StudentAdapter studentAdapter;
    private DatabaseHelper databaseHelper;
    private List<Course> allCourses;
    private List<Course> filteredCourses;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_statistics, container, false);
        
        databaseHelper = new DatabaseHelper(getContext());
        
        spinnerCourses = view.findViewById(R.id.spinner_courses);
        checkBoxActive = view.findViewById(R.id.checkbox_active);
        recyclerView = view.findViewById(R.id.recycler_view_students);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        studentAdapter = new StudentAdapter(getContext(), new ArrayList<>());
        recyclerView.setAdapter(studentAdapter);
        
        loadCourses();
        
        spinnerCourses.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Course selectedCourse = (Course) parent.getItemAtPosition(position);
                loadStudentsForCourse(selectedCourse.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        
        checkBoxActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                filterCoursesByActiveStatus(isChecked);
            }
        });
        
        return view;
    }

    private void loadCourses() {
        allCourses = databaseHelper.getAllCourses();
        filteredCourses = new ArrayList<>(allCourses);
        
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(getContext(), 
                android.R.layout.simple_spinner_item, filteredCourses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapter);
        
        if (!filteredCourses.isEmpty()) {
            spinnerCourses.setSelection(0);
            loadStudentsForCourse(filteredCourses.get(0).getId());
        }
    }

    private void filterCoursesByActiveStatus(boolean showOnlyActive) {
        if (showOnlyActive) {
            filteredCourses = allCourses.stream()
                    .filter(Course::isActive)
                    .collect(Collectors.toList());
        } else {
            filteredCourses = new ArrayList<>(allCourses);
        }
        
        ArrayAdapter<Course> adapter = new ArrayAdapter<>(getContext(), 
                android.R.layout.simple_spinner_item, filteredCourses);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCourses.setAdapter(adapter);
        
        if (!filteredCourses.isEmpty()) {
            spinnerCourses.setSelection(0);
            loadStudentsForCourse(filteredCourses.get(0).getId());
        } else {
            studentAdapter.updateData(new ArrayList<>());
        }
    }

    private void loadStudentsForCourse(int courseId) {
        List<Student> students = databaseHelper.getStudentsByCourse(courseId);
        studentAdapter.updateData(students);
    }
}

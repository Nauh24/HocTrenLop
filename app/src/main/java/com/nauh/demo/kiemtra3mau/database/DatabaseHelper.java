package com.nauh.demo.kiemtra3mau.database;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import com.nauh.demo.kiemtra3mau.model.Course;
import com.nauh.demo.kiemtra3mau.model.Registration;
import com.nauh.demo.kiemtra3mau.model.Student;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "course_management.db";
    private static final int DATABASE_VERSION = 1;

    // Table names
    public static final String TABLE_COURSE = "course";
    public static final String TABLE_STUDENT = "student";
    public static final String TABLE_REGISTRATION = "registration";

    // Common column names
    public static final String COLUMN_ID = "id";

    // Course table columns
    public static final String COLUMN_COURSE_NAME = "course_name";
    public static final String COLUMN_START_DATE = "start_date";
    public static final String COLUMN_END_DATE = "end_date";
    public static final String COLUMN_IS_ACTIVE = "is_active";

    // Student table columns
    public static final String COLUMN_STUDENT_NAME = "student_name";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASSWORD = "password";

    // Registration table columns
    public static final String COLUMN_STUDENT_ID = "student_id";
    public static final String COLUMN_COURSE_ID = "course_id";
    public static final String COLUMN_REGISTRATION_DATE = "registration_date";

    // Create table statements
    private static final String CREATE_TABLE_COURSE = "CREATE TABLE " + TABLE_COURSE + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_COURSE_NAME + " TEXT,"
            + COLUMN_START_DATE + " TEXT,"
            + COLUMN_END_DATE + " TEXT,"
            + COLUMN_IS_ACTIVE + " INTEGER" + ")";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENT + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_NAME + " TEXT,"
            + COLUMN_EMAIL + " TEXT UNIQUE,"
            + COLUMN_PASSWORD + " TEXT" + ")";

    private static final String CREATE_TABLE_REGISTRATION = "CREATE TABLE " + TABLE_REGISTRATION + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_STUDENT_ID + " INTEGER,"
            + COLUMN_COURSE_ID + " INTEGER,"
            + COLUMN_REGISTRATION_DATE + " TEXT,"
            + "FOREIGN KEY(" + COLUMN_STUDENT_ID + ") REFERENCES " + TABLE_STUDENT + "(" + COLUMN_ID + "),"
            + "FOREIGN KEY(" + COLUMN_COURSE_ID + ") REFERENCES " + TABLE_COURSE + "(" + COLUMN_ID + ")" + ")";

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_COURSE);
        db.execSQL(CREATE_TABLE_STUDENT);
        db.execSQL(CREATE_TABLE_REGISTRATION);

        // Add sample data
        insertSampleData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REGISTRATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COURSE);
        onCreate(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Insert sample courses
        ContentValues courseValues1 = new ContentValues();
        courseValues1.put(COLUMN_COURSE_NAME, "Lập trình Android");
        courseValues1.put(COLUMN_START_DATE, "01/05/2023");
        courseValues1.put(COLUMN_END_DATE, "30/07/2023");
        courseValues1.put(COLUMN_IS_ACTIVE, 1);
        long courseId1 = db.insert(TABLE_COURSE, null, courseValues1);

        ContentValues courseValues2 = new ContentValues();
        courseValues2.put(COLUMN_COURSE_NAME, "Lập trình Web");
        courseValues2.put(COLUMN_START_DATE, "15/06/2023");
        courseValues2.put(COLUMN_END_DATE, "15/09/2023");
        courseValues2.put(COLUMN_IS_ACTIVE, 1);
        long courseId2 = db.insert(TABLE_COURSE, null, courseValues2);

        ContentValues courseValues3 = new ContentValues();
        courseValues3.put(COLUMN_COURSE_NAME, "Lập trình iOS");
        courseValues3.put(COLUMN_START_DATE, "10/07/2023");
        courseValues3.put(COLUMN_END_DATE, "10/10/2023");
        courseValues3.put(COLUMN_IS_ACTIVE, 0);
        long courseId3 = db.insert(TABLE_COURSE, null, courseValues3);

        ContentValues courseValues4 = new ContentValues();
        courseValues4.put(COLUMN_COURSE_NAME, "Trí tuệ nhân tạo");
        courseValues4.put(COLUMN_START_DATE, "01/08/2023");
        courseValues4.put(COLUMN_END_DATE, "30/11/2023");
        courseValues4.put(COLUMN_IS_ACTIVE, 1);
        long courseId4 = db.insert(TABLE_COURSE, null, courseValues4);

        ContentValues courseValues5 = new ContentValues();
        courseValues5.put(COLUMN_COURSE_NAME, "Phát triển game");
        courseValues5.put(COLUMN_START_DATE, "15/09/2023");
        courseValues5.put(COLUMN_END_DATE, "15/12/2023");
        courseValues5.put(COLUMN_IS_ACTIVE, 0);
        long courseId5 = db.insert(TABLE_COURSE, null, courseValues5);

        // Insert sample students
        ContentValues studentValues1 = new ContentValues();
        studentValues1.put(COLUMN_STUDENT_NAME, "Nguyễn Văn A");
        studentValues1.put(COLUMN_EMAIL, "nguyenvana@example.com");
        studentValues1.put(COLUMN_PASSWORD, "password123");
        long studentId1 = db.insert(TABLE_STUDENT, null, studentValues1);

        ContentValues studentValues2 = new ContentValues();
        studentValues2.put(COLUMN_STUDENT_NAME, "Trần Thị B");
        studentValues2.put(COLUMN_EMAIL, "tranthib@example.com");
        studentValues2.put(COLUMN_PASSWORD, "password123");
        long studentId2 = db.insert(TABLE_STUDENT, null, studentValues2);

        ContentValues studentValues3 = new ContentValues();
        studentValues3.put(COLUMN_STUDENT_NAME, "Lê Văn C");
        studentValues3.put(COLUMN_EMAIL, "levanc@example.com");
        studentValues3.put(COLUMN_PASSWORD, "password123");
        long studentId3 = db.insert(TABLE_STUDENT, null, studentValues3);

        ContentValues studentValues4 = new ContentValues();
        studentValues4.put(COLUMN_STUDENT_NAME, "Phạm Thị D");
        studentValues4.put(COLUMN_EMAIL, "phamthid@example.com");
        studentValues4.put(COLUMN_PASSWORD, "password123");
        long studentId4 = db.insert(TABLE_STUDENT, null, studentValues4);

        ContentValues studentValues5 = new ContentValues();
        studentValues5.put(COLUMN_STUDENT_NAME, "Hoàng Văn E");
        studentValues5.put(COLUMN_EMAIL, "hoangvane@example.com");
        studentValues5.put(COLUMN_PASSWORD, "password123");
        long studentId5 = db.insert(TABLE_STUDENT, null, studentValues5);

        // Insert sample registrations
        // Student 1 registers for Course 1 and 2
        ContentValues regValues1 = new ContentValues();
        regValues1.put(COLUMN_STUDENT_ID, studentId1);
        regValues1.put(COLUMN_COURSE_ID, courseId1);
        regValues1.put(COLUMN_REGISTRATION_DATE, "05/05/2023");
        db.insert(TABLE_REGISTRATION, null, regValues1);

        ContentValues regValues2 = new ContentValues();
        regValues2.put(COLUMN_STUDENT_ID, studentId1);
        regValues2.put(COLUMN_COURSE_ID, courseId2);
        regValues2.put(COLUMN_REGISTRATION_DATE, "20/06/2023");
        db.insert(TABLE_REGISTRATION, null, regValues2);

        // Student 2 registers for Course 1, 3 and 4
        ContentValues regValues3 = new ContentValues();
        regValues3.put(COLUMN_STUDENT_ID, studentId2);
        regValues3.put(COLUMN_COURSE_ID, courseId1);
        regValues3.put(COLUMN_REGISTRATION_DATE, "10/05/2023");
        db.insert(TABLE_REGISTRATION, null, regValues3);

        ContentValues regValues4 = new ContentValues();
        regValues4.put(COLUMN_STUDENT_ID, studentId2);
        regValues4.put(COLUMN_COURSE_ID, courseId3);
        regValues4.put(COLUMN_REGISTRATION_DATE, "15/07/2023");
        db.insert(TABLE_REGISTRATION, null, regValues4);

        ContentValues regValues5 = new ContentValues();
        regValues5.put(COLUMN_STUDENT_ID, studentId2);
        regValues5.put(COLUMN_COURSE_ID, courseId4);
        regValues5.put(COLUMN_REGISTRATION_DATE, "05/08/2023");
        db.insert(TABLE_REGISTRATION, null, regValues5);

        // Student 3 registers for Course 2 and 5
        ContentValues regValues6 = new ContentValues();
        regValues6.put(COLUMN_STUDENT_ID, studentId3);
        regValues6.put(COLUMN_COURSE_ID, courseId2);
        regValues6.put(COLUMN_REGISTRATION_DATE, "25/06/2023");
        db.insert(TABLE_REGISTRATION, null, regValues6);

        ContentValues regValues7 = new ContentValues();
        regValues7.put(COLUMN_STUDENT_ID, studentId3);
        regValues7.put(COLUMN_COURSE_ID, courseId5);
        regValues7.put(COLUMN_REGISTRATION_DATE, "20/09/2023");
        db.insert(TABLE_REGISTRATION, null, regValues7);

        // Student 4 registers for Course 3 and 4
        ContentValues regValues8 = new ContentValues();
        regValues8.put(COLUMN_STUDENT_ID, studentId4);
        regValues8.put(COLUMN_COURSE_ID, courseId3);
        regValues8.put(COLUMN_REGISTRATION_DATE, "12/07/2023");
        db.insert(TABLE_REGISTRATION, null, regValues8);

        ContentValues regValues9 = new ContentValues();
        regValues9.put(COLUMN_STUDENT_ID, studentId4);
        regValues9.put(COLUMN_COURSE_ID, courseId4);
        regValues9.put(COLUMN_REGISTRATION_DATE, "10/08/2023");
        db.insert(TABLE_REGISTRATION, null, regValues9);

        // Student 5 registers for Course 5
        ContentValues regValues10 = new ContentValues();
        regValues10.put(COLUMN_STUDENT_ID, studentId5);
        regValues10.put(COLUMN_COURSE_ID, courseId5);
        regValues10.put(COLUMN_REGISTRATION_DATE, "18/09/2023");
        db.insert(TABLE_REGISTRATION, null, regValues10);
    }


    // Course CRUD operations
    public long addCourse(Course course) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_COURSE_NAME, course.getName());
        values.put(COLUMN_START_DATE, dateFormat.format(course.getStartDate()));
        values.put(COLUMN_END_DATE, dateFormat.format(course.getEndDate()));
        values.put(COLUMN_IS_ACTIVE, course.isActive() ? 1 : 0);

        long id = db.insert(TABLE_COURSE, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE + " ORDER BY " + COLUMN_START_DATE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                course.setName(cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_NAME)));

                try {
                    course.setStartDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE))));
                    course.setEndDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                course.setActive(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ACTIVE)) == 1);
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return courseList;
    }

    @SuppressLint("Range")
    public List<Course> searchCoursesByName(String keyword) {
        List<Course> courseList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_COURSE +
                " WHERE " + COLUMN_COURSE_NAME + " LIKE '%" + keyword + "%'" +
                " ORDER BY " + COLUMN_START_DATE;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Course course = new Course();
                course.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                course.setName(cursor.getString(cursor.getColumnIndex(COLUMN_COURSE_NAME)));

                try {
                    course.setStartDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_START_DATE))));
                    course.setEndDate(dateFormat.parse(cursor.getString(cursor.getColumnIndex(COLUMN_END_DATE))));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                course.setActive(cursor.getInt(cursor.getColumnIndex(COLUMN_IS_ACTIVE)) == 1);
                courseList.add(course);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return courseList;
    }

    // Student CRUD operations
    public long addStudent(Student student) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_NAME, student.getName());
        values.put(COLUMN_EMAIL, student.getEmail());
        values.put(COLUMN_PASSWORD, student.getPassword());

        long id = db.insert(TABLE_STUDENT, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public Student getStudentByEmailAndPassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT +
                " WHERE " + COLUMN_EMAIL + " = '" + email + "'" +
                " AND " + COLUMN_PASSWORD + " = '" + password + "'";

        Cursor cursor = db.rawQuery(selectQuery, null);
        Student student = null;

        if (cursor.moveToFirst()) {
            student = new Student();
            student.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
            student.setName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)));
            student.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
            student.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
        }

        cursor.close();
        db.close();
        return student;
    }

    // Registration CRUD operations
    public long registerCourse(Registration registration) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STUDENT_ID, registration.getStudentId());
        values.put(COLUMN_COURSE_ID, registration.getCourseId());
        values.put(COLUMN_REGISTRATION_DATE, dateFormat.format(registration.getRegistrationDate()));

        long id = db.insert(TABLE_REGISTRATION, null, values);
        db.close();
        return id;
    }

    @SuppressLint("Range")
    public List<Student> getStudentsByCourse(int courseId) {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT s.* FROM " + TABLE_STUDENT + " s" +
                " INNER JOIN " + TABLE_REGISTRATION + " r ON s." + COLUMN_ID + " = r." + COLUMN_STUDENT_ID +
                " WHERE r." + COLUMN_COURSE_ID + " = " + courseId;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }

    public boolean isStudentRegisteredForCourse(int studentId, int courseId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_REGISTRATION +
                " WHERE " + COLUMN_STUDENT_ID + " = " + studentId +
                " AND " + COLUMN_COURSE_ID + " = " + courseId;

        Cursor cursor = db.rawQuery(selectQuery, null);
        boolean isRegistered = cursor.getCount() > 0;

        cursor.close();
        db.close();
        return isRegistered;
    }

    @SuppressLint("Range")
    public List<Student> getAllStudents() {
        List<Student> studentList = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Student student = new Student();
                student.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)));
                student.setName(cursor.getString(cursor.getColumnIndex(COLUMN_STUDENT_NAME)));
                student.setEmail(cursor.getString(cursor.getColumnIndex(COLUMN_EMAIL)));
                student.setPassword(cursor.getString(cursor.getColumnIndex(COLUMN_PASSWORD)));
                studentList.add(student);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return studentList;
    }

    public String getRegistrationsAsString() {
        StringBuilder result = new StringBuilder();
        result.append("ID | Học viên ID | Khóa học ID | Ngày đăng ký\n");
        result.append("--------------------------------------------------\n");

        String selectQuery = "SELECT r.*, s.student_name, c.course_name FROM " + TABLE_REGISTRATION + " r" +
                " INNER JOIN " + TABLE_STUDENT + " s ON r." + COLUMN_STUDENT_ID + " = s." + COLUMN_ID +
                " INNER JOIN " + TABLE_COURSE + " c ON r." + COLUMN_COURSE_ID + " = c." + COLUMN_ID;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") int studentId = cursor.getInt(cursor.getColumnIndex(COLUMN_STUDENT_ID));
                @SuppressLint("Range") int courseId = cursor.getInt(cursor.getColumnIndex(COLUMN_COURSE_ID));
                @SuppressLint("Range") String regDate = cursor.getString(cursor.getColumnIndex(COLUMN_REGISTRATION_DATE));
                @SuppressLint("Range") String studentName = cursor.getString(cursor.getColumnIndex("student_name"));
                @SuppressLint("Range") String courseName = cursor.getString(cursor.getColumnIndex("course_name"));

                result.append(id).append(" | ")
                        .append(studentId).append(" (").append(studentName).append(") | ")
                        .append(courseId).append(" (").append(courseName).append(") | ")
                        .append(regDate).append("\n");
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return result.toString();
    }
}

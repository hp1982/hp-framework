package com.huipeng1982.utils.mapper.model;

import java.util.List;

public class Student {
    public String name;
    private int age;
    private Teacher teacher;
    private List<String> course;

    public Student() {
    }

    public Student(String name, int age, Teacher teacher, List<String> course) {
        this.name = name;
        this.age = age;
        this.teacher = teacher;
        this.course = course;
    }

    public List<String> getCourse() {
        return course;
    }

    public void setCourse(List<String> course) {
        this.course = course;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

package com.huipeng1982.utils.mapper.model;

import java.util.List;

public class StudentVO {
    public String name;
    private int age;
    private TeacherVO teacher;
    private List<String> course;

    public StudentVO() {
    }

    public StudentVO(String name, int age, TeacherVO teacher, List<String> course) {
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

    public TeacherVO getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherVO teacher) {
        this.teacher = teacher;
    }
}

package com.example.unit.app.mock;

import java.util.List;

/**
 * @author lisen
 * @since 09-03-2018
 */

public class ModelImpl {
    private String name = "default_name";
    private int age;
    private boolean isStudent;
    private List<String> subjects;
    private long height;

    public String getName() {
        return name;
    }

    public String setName(String name) {
        this.name = name;
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }

    public void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean setStudent(boolean student) {
        isStudent = student;
        return student;
    }

    public List<String> setSubjects(List<String> subjects) {
        this.subjects = subjects;
        return subjects;
    }

    public long setHeight(long height) {
        this.height = height;
        return height;
    }
}

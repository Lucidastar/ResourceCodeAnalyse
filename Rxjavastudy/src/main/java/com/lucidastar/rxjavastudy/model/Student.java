package com.lucidastar.rxjavastudy.model;

/**
 * @author hyli
 * @date 2020/5/14 22:25
 * GitHub：https://github.com/Lucidastar
 * email： 1061150853@qq.com
 * description：
 */
public class Student {
    private String name;
    private int age;
    private String score;

    public Student() {
    }

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}

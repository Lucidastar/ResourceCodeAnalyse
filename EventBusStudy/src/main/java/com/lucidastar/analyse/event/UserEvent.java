package com.lucidastar.analyse.event;

/**
 * Created by qiuyouzone on 2018/7/19.
 */

public class UserEvent {
    private String name;
    private String age;

    public UserEvent() {
    }

    public UserEvent(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}

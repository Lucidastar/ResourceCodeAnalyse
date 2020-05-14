package com.lucidastar.rxjavastudy.model;

/**
 * @author hyli
 * @date 2020/5/14 22:29
 * GitHub：https://github.com/Lucidastar
 * email： 1061150853@qq.com
 * description：
 */
public class ClassModel {
    private String mClassName;
    private String mStudentId;
    private int mStudentNum;

    public ClassModel() {
    }

    public ClassModel(String mClassName, String mStudentId, int mStudentNum) {
        this.mClassName = mClassName;
        this.mStudentId = mStudentId;
        this.mStudentNum = mStudentNum;
    }

    public String getmClassName() {
        return mClassName;
    }

    public void setmClassName(String mClassName) {
        this.mClassName = mClassName;
    }

    public String getmStudentId() {
        return mStudentId;
    }

    public void setmStudentId(String mStudentId) {
        this.mStudentId = mStudentId;
    }

    public int getmStudentNum() {
        return mStudentNum;
    }

    public void setmStudentNum(int mStudentNum) {
        this.mStudentNum = mStudentNum;
    }
}

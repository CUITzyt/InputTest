package com.skyworth.inputtest.xmlparse;

import java.util.ArrayList;

public class ClassBean {

    private String id;
    private String name;
    private ArrayList<StudentBean> mClassBeans;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<StudentBean> getmClassBeans() {
        return mClassBeans;
    }

    public void setmStudentBeans(ArrayList<StudentBean> mClassBeans) {
        this.mClassBeans = mClassBeans;
    }

}

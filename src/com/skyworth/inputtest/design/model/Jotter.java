package com.skyworth.inputtest.design.model;

public class Jotter {

    private VolAdapter mAdapter;

    public VolAdapter getmAdapter() {
        return mAdapter;
    }

    public void setmAdapter(VolAdapter mAdapter) {
        this.mAdapter = mAdapter;
    }

    public int inputVoltage() {
        return mAdapter.transVoltage();
    }
}

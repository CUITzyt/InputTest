package com.skyworth.inputtest.design.model;

public class MyVolAdapter implements VolAdapter {

    private PowerSupplyDevice mSupplyDevice;
    private int voltage;

    public PowerSupplyDevice getmSupplyDevice() {
        return mSupplyDevice;
    }

    public void setmSupplyDevice(PowerSupplyDevice mSupplyDevice) {
        this.mSupplyDevice = mSupplyDevice;
    }

    public int getVoltage() {
        return voltage;
    }

    public void setVoltage(int voltage) {
        this.voltage = voltage;
    }

    @Override
    public int transVoltage() {
        // TODO Auto-generated method stub
        int retLowVoltage = mSupplyDevice.powerSupplyVoltage() / 14;
        
        return retLowVoltage;
    }

}

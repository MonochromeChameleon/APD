package com.monochromechameleon.apd.cw1;

public class Heater {

    private final double thermostatTemperature; // theta
    private final double overheat; // d
    private final double heaterFactor; // h
    private boolean on;
    
    public Heater(double theta, double d, double h) {
        thermostatTemperature = theta;
        overheat = d;
        heaterFactor = h;
        on = true;
    }
    
    public boolean isOn() {
        return on;
    }
    
    public double getHeaterFactor() {
        return heaterFactor;
    }
    
    public void updateOnOffStatus(double roomTemperature) {
        if (on && roomTemperature > thermostatTemperature + overheat) {
            on = false;
        } else if (roomTemperature < thermostatTemperature) {
            on = true;
        }
    }
}

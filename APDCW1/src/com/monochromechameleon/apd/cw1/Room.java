package com.monochromechameleon.apd.cw1;

public class Room {
    private double temperature; // t
    private final double heatExchangeFactor; // f
    private final double areaFactor; // a
    
    private double externalTemperature; // e
    private final double rateOfExternalTemperatureChange;
    
    private final Heater heater;
    
    public Room(double t, double f, double a, double e, double r, Heater h) {
        temperature = t;
        heatExchangeFactor = f;
        areaFactor = a;
        externalTemperature = e;
        rateOfExternalTemperatureChange = r;
        
        heater = h;
    }
    
    public double heatLoss(double externalTemperature) {
        return (temperature - externalTemperature) * heatExchangeFactor;
    }
    
    public double heatGain(double heaterFactor) {
        return heaterFactor / areaFactor;
    }
    
    public double getTemperature() {
        return temperature;
    }
    
    public double getExternalTemperature() {
        return externalTemperature;
    }
    
    public void tick() {
        double loss = heatLoss(externalTemperature);
        double gain = heater.isOn() ? heatGain(heater.getHeaterFactor()) : 0;
        double temperatureChange = gain - loss;
        temperature += temperatureChange;
        externalTemperature += rateOfExternalTemperatureChange;
        
        heater.updateOnOffStatus(temperature);
    }
}

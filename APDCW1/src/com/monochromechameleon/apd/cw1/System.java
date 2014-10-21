package com.monochromechameleon.apd.cw1;

import java.io.FileWriter;
import java.io.IOException;

public class System {
    private final Room room;
    private final Heater heater;
    
    public System(double t) {
        heater = new Heater(22, 2, 30);
        room = new Room(20, 0.05, 50, 18, t, heater);
    }
    
    private void outputHeaderLine(FileWriter out) throws IOException {
        String headers = "Period number, Room Temperature, Heater status, Outside temperature";
        out.append(headers);
        out.append("\n");
    }
    
    private void outputValues(int period, FileWriter out) throws IOException {
        String line = String.format(
            "%d, %.2f, %s, %.1f",
            period,
            room.getTemperature(),
            heater.isOn() ? "on" : "off",
            room.getExternalTemperature()
        );
        
        out.append(line);
        out.append("\n");
    }
    
    public void run(int numberOfTimePeriods, String filename) throws IOException {
        
        try (FileWriter out = new FileWriter(filename)) {
            outputHeaderLine(out);
            outputValues(0, out);
            for (int i = 1; i < numberOfTimePeriods; i++) {
                room.tick();
                
                outputValues(i, out);
            }
        }
    }
}

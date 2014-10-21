Exercise 1: Room Heating Simulation

For the purposes of this simulation, assume the following:
If the temperature inside a room is t, and the temperature outside it is e, in any one time 
period the loss of temperature in the room due to heat exchange through its walls is 
(t-e)*f where f is a factor which is constant for any particular room.

If a heater is on in the room, in any one time period the gain in temperature due to the 
heater is h/a where h is a constant factor for any particular heater, and a is a factor (the 
area) which is constant for any particular room.

A heater is set by a thermostat to a temperature θ. If the temperature in the room where 
the heater is situated drops below θ, the heater is switched on automatically. If the 
temperature in the room rises above θ+d, where d is called the “overheat”, the heater is 
automatically switched off.

Your task in this exercise is to simulate a room over a period of time with temperature 
rising and falling according to the outside temperature and a heater in the room set by a 
thermostat. Assume the initial temperature outside the room is 18.0 and the initial 
temperature inside the room is 20.0. The thermostat on temperature θ is set at 22.0, and 
the overheat value d is 2.0. The heat exchange factor of the room f is 0.05, and the area 
factor of the room a is 50.0. The heating factor h of the heater is 30.0. For the purpose 
of this exercise you need not be concerned about the actual units these figures are given 
in, or over any other possible factors not mentioned here.

Write a Java program which shows how the temperature in the room varies and the heater 
is switched on and off over a period of 100 time units, assuming the outside temperature 
remains constant. Then modify your program to simulate a situation where the outside 
temperature starts at 18.0 as before, but drops by 0.1 degrees every time unit. The 
submission you hand in should be this second version with the varying outside 
temperature.

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
public class LinearSlide {
    private final DcMotorEx slider;
    private final int[] stoppingPoints;
    PIDFcontroller controller;
    private int pointer; //A POINTer to the stopping POINTS
    private final int numberOfPoints;

    private boolean runningHighMacro = false;
    private boolean runningLowMacro = false;

    public LinearSlide(DcMotorEx slider, int[] points, int currIndex, PIDFcontroller PID){
        this.slider = slider;
        stoppingPoints = points;
        Arrays.sort(stoppingPoints); // Allows us to use ++ and -- to move through the points
        numberOfPoints = stoppingPoints.length;
        controller = PID;
        pointer = currIndex;
    }
    public LinearSlide(HardwareMap hardwareMap, String Name, int[] points, int currIndex){
        this(hardwareMap.get(DcMotorEx.class, Name), points, currIndex, new PIDFcontroller(0.0,0.0,0.0,0.0,0.0,10,0.8) );
    }
    public LinearSlide(HardwareMap hardwareMap, String Name, int[] points){
        this(hardwareMap,Name, points, 0);
    }
    public void update(boolean up, boolean down, boolean highest, boolean bottom){
        if(highest){
            runningHighMacro = true;
            runningLowMacro = false;
        } else if (bottom){
            runningHighMacro = false;
            runningLowMacro = true;
        }

        if(!isTop() && up) {
            runningHighMacro = runningLowMacro = false;
            slider.setPower(controller.CalculateAsnyc(stoppingPoints[++pointer], slider.getCurrentPosition()));
        } else if (!isBottom() && down){
            runningHighMacro = runningLowMacro = false;
            slider.setPower(controller.CalculateAsnyc(stoppingPoints[--pointer], slider.getCurrentPosition()));
        }

        // As soon as the press the high/low button, it will run to completion _unless_ they press another button (like up/down)
        else if (runningHighMacro) {
            slider.setPower(controller.CalculateAsnyc(stoppingPoints[pointer = numberOfPoints - 1],slider.getCurrentPosition()));
        } else if (runningLowMacro) {
            slider.setPower(controller.CalculateAsnyc(stoppingPoints[pointer =0],slider.getCurrentPosition()));
        } else {
            slider.setPower(0);
        }
    }
    boolean isTop(){
        return pointer >= numberOfPoints - 1;
    }
    boolean isBottom(){
        return pointer <= 0;
    }
}

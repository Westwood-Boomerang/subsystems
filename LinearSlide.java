package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.Arrays;
public class LinearSlide {
    private DcMotorEx slider;
    private int[] StoppingPoints;
    PIDFcontroller controller;
    private int Pointer; //A POINTer to the stopping POINTS
    private int NumberOfPoints;

    public LinearSlide(HardwareMap hardwareMap, String Name, int[] points, int currIndex, PIDFcontroller PID){
        slider = hardwareMap.get(DcMotorEx.class, Name);
        StoppingPoints = points;
        Arrays.sort(StoppingPoints); // Allows us to use ++ and -- to move through the points
        NumberOfPoints = StoppingPoints.length-1;
        controller = PID;
        Pointer = currIndex;
    }
    public LinearSlide(HardwareMap hardwareMap, String Name, int[] points, int currIndex){
        this(hardwareMap, Name, points, currIndex, new PIDFcontroller(0.0,0.0,0.0,0.0,0.0,10,5.0) );
    }
    public LinearSlide(HardwareMap hardwareMap, String Name, int[] points){
        this(hardwareMap,Name, points, 0);
    }
    public void update(boolean up, boolean down, boolean highest, boolean bottom){
        if(highest){
          controller.CalculateAsnyc(StoppingPoints[Pointer=NumberOfPoints],slider.getCurrentPosition());
        }
        if (bottom){
            controller.CalculateAsnyc(StoppingPoints[Pointer=0],slider.getCurrentPosition());
        }
        if(Pointer != NumberOfPoints && up) {
            controller.CalculateAsnyc(StoppingPoints[++Pointer], slider.getCurrentPosition());
        }
        if (Pointer != 0 && down){
            controller.CalculateAsnyc(StoppingPoints[--Pointer], slider.getCurrentPosition());
        }
    }
    boolean isTop(){
        return Pointer == NumberOfPoints;
    }
    boolean isBottom(){
        return Pointer == 0;
    }
}

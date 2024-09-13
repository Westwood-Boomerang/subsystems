package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.teamcode.subsystems.PIDFcontroller;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import java.util.Arrays;
public class linearSlide {
    private DcMotorEx slider;
    private int[] StoppingPoints;
    PIDFcontroller controller;
    private int Pointer; //A POINTer to the stopping POINTS
    private int NumberOfPoints;

    public linearSlide(HardwareMap hardwareMap, String Name, int[] points, int currIndex, PIDFcontroller PID){
        slider = hardwareMap.get(DcMotorEx.class, Name);
        StoppingPoints = points;
        Arrays.sort(StoppingPoints); // Allows us to use ++ and -- to move through the points
        NumberOfPoints = Arrays.size();
        controller = PID;
        Pointer = currIndex;
    }
    public linearSlide(HardwareMap hardwareMap, String Name, int[] points, int currIndex){
        this(hardwareMap, Name, points, currIndex, new PIDFcontroller(0.0,0.0,0.0,0.0,0.0,10,5.0) );
    }
    public linearSlide(HardwareMap hardwareMap, String Name, int[] points){
        this(hardwareMap,Name, points, 0);
    }
    public void update(boolean up, boolean down, boolean bottom, boolean highest){
        if(highest){
          controller.CalculateAsnyc(StoppingPoints[Pointer=NumberOfPoints],slider.getCurrentPostion());
        } else if (bottom){
            controller.CalculateAsnyc(StoppingPoints[Pointer=0],slider.getCurrentPostion());
        } else if(up) {
            controller.CalculateAsnyc(StoppingPoints[++Pointer], slider.getCurrentPosition());
        } else if (down){
            controller.CalculateAsnyc(StoppingPoints[--Pointer], slider.getCurrentPosition());
        }
    }
}

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ClawServo {
    Servo claw;
    public ClawServo(HardwareMap hardwareMap, String Name, boolean reverse, double rangeMin, double rangeMax){
        claw = hardwareMap.get(Servo.class, Name);
        claw.scaleRange(rangeMin, rangeMax);
        if(reverse){
            claw.setDirection(Servo.Direction.REVERSE);
        }
    }
    public ClawServo(HardwareMap hardwareMap, String Name, boolean reverse){
        this(hardwareMap, Name, reverse, 0.0, 1.0);
    }
    public ClawServo(HardwareMap hardwareMap, String Name){
        this(hardwareMap, Name, false);
    }
    public void update(boolean open, boolean close){
        if(open){
            claw.setPosition(1.0);
        }
        if (close){
            claw.setPosition(0.0);
        }
    }
    boolean isOpen(){
        return claw.getPosition()==1.0;
    }
    boolean isClosed(){
        return claw.getPosition()==0.0;
    }

}

package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class clawServo {
    Servo claw;
    clawServo(HardwareMap hardwareMap, String Name, boolean reverse, double rangeMin, double rangeMax){
        claw = hardwareMap.get(Servo.class, Name);
        claw.scaleRange(rangeMin, rangeMax);
        if(reverse){
            claw.setDirection(Servo.Direction.REVERSE);
        }
    }
    clawServo(HardwareMap hardwareMap, String Name, boolean reverse){
        this(hardwareMap, Name, reverse, 0.0, 1.0);
    }
    clawServo(HardwareMap hardwareMap, String Name){
        this(hardwareMap, Name, false);
    }
    public void update(boolean open, boolean close){
        if(open){
            claw.setPosition(1.0);
        } else if (close){
            claw.setPosition(0.0);
        }
    }
}

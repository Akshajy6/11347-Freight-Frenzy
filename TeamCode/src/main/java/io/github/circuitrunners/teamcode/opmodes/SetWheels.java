package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class SetWheels extends LinearOpMode {
    io.github.circuitrunners.teamcode.swerveutils.SwankDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new io.github.circuitrunners.teamcode.swerveutils.SwankDrive(new Servo[]{
                hardwareMap.get(Servo.class, "leftbackservo"),
                hardwareMap.get(Servo.class, "rightbackservo"),
                hardwareMap.get(Servo.class, "leftfrontservo"),
                hardwareMap.get(Servo.class, "rightfrontservo")
        }, new DcMotor[]{
                hardwareMap.get(DcMotor.class, "leftbackmotor"),
                hardwareMap.get(DcMotor.class, "rightbackmotor"),
                hardwareMap.get(DcMotor.class, "leftfrontmotor"),
                hardwareMap.get(DcMotor.class, "rightfrontmotor"),
        });

        drive.setAllZero();
    }
}

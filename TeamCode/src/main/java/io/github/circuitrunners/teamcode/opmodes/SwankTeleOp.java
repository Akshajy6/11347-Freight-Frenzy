package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

import io.github.circuitrunners.teamcode.swerveutils.SwankDrive;

@TeleOp
public class SwankTeleOp extends OpMode {
    io.github.circuitrunners.teamcode.swerveutils.SwankDrive drive;
    CRServo carouselMech;

    @Override
    public void init() {
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

        carouselMech = hardwareMap.get(CRServo.class, "carouselservo");
        carouselMech.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    @Override
    public void loop() {
        drive.swankDrive(gamepad1.left_stick_y, gamepad1.right_stick_y);
        carouselMech.setPower((gamepad2.a) ? 1 : (gamepad2.b ? -1 : 0));
    }
}
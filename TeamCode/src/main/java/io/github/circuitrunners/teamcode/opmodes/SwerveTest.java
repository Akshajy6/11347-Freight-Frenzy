package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

import io.github.circuitrunners.teamcode.swerveutils.SwerveDrive;
import io.github.circuitrunners.teamcode.swerveutils.SwerveWheel;

@TeleOp
public class SwerveTest extends OpMode {
    SwerveDrive robot;
    CRServo carouselMech;

    @Override
    public void init() {
        SwerveWheel leftFront = new SwerveWheel(hardwareMap.get(DcMotor.class, "leftfrontmotor"), hardwareMap.get(Servo.class, "leftfrontservo"));
        SwerveWheel leftBack = new SwerveWheel(hardwareMap.get(DcMotor.class, "leftbackmotor"), hardwareMap.get(Servo.class, "leftbackservo"));
        SwerveWheel rightFront = new SwerveWheel(hardwareMap.get(DcMotor.class, "rightfrontmotor"), hardwareMap.get(Servo.class, "rightfrontservo"));
        SwerveWheel rightBack = new SwerveWheel(hardwareMap.get(DcMotor.class, "rightbackmotor"), hardwareMap.get(Servo.class, "rightbackservo"));
        
        robot = new SwerveDrive(leftFront, leftBack, rightFront, rightBack);
        robot.driveSwerve(0, 1, 0, false);

        carouselMech = hardwareMap.get(CRServo.class, "carouselservo");
    }

    @Override
    public void loop() {
        robot.driveSwerve(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x, false);
    }
}

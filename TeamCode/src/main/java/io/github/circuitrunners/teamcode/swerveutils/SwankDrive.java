package io.github.circuitrunners.teamcode.swerveutils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import static java.lang.Thread.sleep;

/**
 * Only use me in an emergency (i.e. servos aren't working right) or for auto stuff (fair, it's easier)
 */
public class SwankDrive {
    static double MOTOR_MAX = 0.8;

    Servo[] servos;
    DcMotor[] motors;

    public SwankDrive(Servo[] theServos, DcMotor[] theMotors) { // back left, back right, front left, front right
        servos = theServos;
        motors = theMotors;

        setAllZero();

        for (DcMotor m : motors) m.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
    }

    public void swankDrive(double l, double r) {
        for (Servo s : servos) s.setPosition(0.5); // sets all servos forwards

        for (int i = 0; i < motors.length; i++) motors[i].setPower(((i % 2 == 0) ? l : r) * ((i == 1) ? 1 : -1) * MOTOR_MAX);
    }

    public void setAllZero() {
        servos[1].setPosition(2.0/3);
        servos[2].setPosition(2.0/3);

        servos[0].setPosition(1.0/3);
        servos[3].setPosition(1.0/3);
    }


}

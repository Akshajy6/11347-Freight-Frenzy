package io.github.circuitrunners.teamcode.swerveutils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class SwerveWheel {

    private DcMotor motor;
    private Servo servo; // This servo should be configured such that 0 is to the right and 1 is a full turn counterclockwise

    private double currentHeading;

    private boolean isMotorReversed;

    public SwerveWheel(DcMotor aMotor, Servo aServo) {
        motor = aMotor;
        servo = aServo;
    }

    public void setDirectionRect(double x, double y) {
        setDirectionRadial(Math.atan2(y, x), Math.hypot(y, x));
    }

    public void setDirectionRadial(double theta, double r) {
        // Most of the code in this function makes sure that the wheel does not need to turn more than 90 degrees.
        currentHeading = servo.getPosition() * 2 * Math.PI; // Gets the current heading of the wheel in radians.

        // If the wheel would have to turn more than 90 degrees to go to the new heading, instead go the opposite direction and reverse the motor.
        if ((theta - currentHeading) > (Math.PI / 2)) {
            theta -= Math.PI;
            isMotorReversed = !isMotorReversed;
        }
        else if ((theta - currentHeading) < (-Math.PI / 2)) {
            theta += Math.PI;
            isMotorReversed = !isMotorReversed;
        }

        servo.setPosition(theta / (2 * Math.PI)); // This sets the servo's position, scaling the 0 to 2PI scale to a 0 to 1 scale.
        motor.setPower(r * ((isMotorReversed) ? -1 : 1)); // This sets the motor power, reversing if required.
    }

    // The forceDirection[something] functions ignore the checks to put the
    protected void forceDirectionRect(double x, double y) {
        forceDirectionRadial(Math.atan2(y, x), Math.hypot(y, x));
    }

    protected void forceDirectionRadial(double theta, double r) {
        isMotorReversed = false;
        servo.setPosition(theta / (2 * Math.PI));
        motor.setPower(r);
    }

    public double getRotation() {
        return servo.getPosition() * 2 * Math.PI;
    }

    public double getPower() {
        return motor.getPower();
    }
}

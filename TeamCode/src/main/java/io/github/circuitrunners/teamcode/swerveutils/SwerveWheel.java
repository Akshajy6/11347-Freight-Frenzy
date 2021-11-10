package io.github.circuitrunners.teamcode.swerveutils;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class SwerveWheel {

    private static double MAX_MOTOR_POWER = 0.8;

    private DcMotor motor;
    private Servo servo; // This servo should be configured such that 0 is to the right and 1 is a full turn counterclockwise

    private double currentHeading;

    private boolean isMotorReversed;

    public SwerveWheel(DcMotor aMotor, Servo aServo) {
        motor = aMotor;
        servo = aServo;
    }

    public void setMovementRect(double x, double y) {
        setMovementRadial(Math.atan2(y, x), Math.hypot(y, x));
    }

    public void setMovementRadial(double theta, double r) {
        // Most of the code in this function makes sure that the wheel does not need to turn more than 90 degrees.
        currentHeading = servo.getPosition() * 3 * Math.PI / 2; // Gets the current heading of the wheel in radians.

        // If the wheel would have to turn more than 90 degrees to go to the new heading, instead go the opposite direction and reverse the motor.
        if (theta > Math.PI / 2 && theta < Math.PI); // If the new heading is across from the zone that the servo can't access, nothing changes with the heading
        else if (theta > 3 * Math.PI / 2) { // If the heading is in the zone the servo can't access it gets reversed to the zone the servo can access
            theta -= Math.PI;
            isMotorReversed = !isMotorReversed;
        }
        else if ((theta - currentHeading) > (Math.PI / 2)) { // The next two if statements do the same thing. If the heading would change more than 90 degrees it instead moves in the other direction and changes less than 90 degrees
            theta -= Math.PI;
            isMotorReversed = !isMotorReversed;
        }
        else if ((theta - currentHeading) < (-Math.PI / 2)) {
            theta += Math.PI;
            isMotorReversed = !isMotorReversed;
        }

        servo.setPosition(theta / (3 * Math.PI / 2)); // This sets the servo's position, scaling the 0 to 3PI/2 scale to a 0 to 1 scale.
        motor.setPower(r * ((isMotorReversed) ? -1 : 1) * MAX_MOTOR_POWER); // This sets the motor power, reversing if required.
    }

    protected void setMovementRadial(double theta, double r, boolean force) {
        // Most of the code in this function makes sure that the wheel does not need to turn more than 90 degrees.
        currentHeading = servo.getPosition() * 3 * Math.PI / 2; // Gets the current heading of the wheel in radians.

        if (force) { // This lets you force the wheel to a certain direction. Use responsibly.
            isMotorReversed = false;
            servo.setPosition(theta / (3 * Math.PI / 2));
            motor.setPower(r * ((isMotorReversed) ? -1 : 1) * MAX_MOTOR_POWER);
            return;
        }

        // If the wheel would have to turn more than 90 degrees to go to the new heading, instead go the opposite direction and reverse the motor.
        if (theta > Math.PI / 2 && theta < Math.PI); // If the new heading is across from the zone that the servo can't access, nothing changes with the heading
        else if (theta > 3 * Math.PI / 2) { // If the heading is in the zone the servo can't access it gets reversed to the zone the servo can access
            theta -= Math.PI;
            isMotorReversed = !isMotorReversed;
        }
        else if ((theta - currentHeading) > (Math.PI / 2)) { // The next two if statements do the same thing. If the heading would change more than 90 degrees it instead moves in the other direction and changes less than 90 degrees
            theta -= Math.PI;
            isMotorReversed = !isMotorReversed;
        }
        else if ((theta - currentHeading) < (-Math.PI / 2)) {
            theta += Math.PI;
            isMotorReversed = !isMotorReversed;
        }

        servo.setPosition(theta / (3 * Math.PI / 2)); // This sets the servo's position, scaling the 0 to 3PI/2 scale to a 0 to 1 scale.
        motor.setPower(r * ((isMotorReversed) ? -1 : 1) * MAX_MOTOR_POWER); // This sets the motor power, reversing if required.
    }

    // The forceDirection[something] functions ignore the checks to put the
    protected void setMovementRect(double x, double y, boolean force) {
        setMovementRadial(Math.atan2(y, x), Math.hypot(y, x), force);
    }

    public double getRotation() {
        return servo.getPosition() * 3 * Math.PI / 2;
    }

    public double getPower() {
        return motor.getPower();
    }
}

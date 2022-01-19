package io.github.circuitrunners.teamcode.submechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import static android.os.SystemClock.sleep;

public class DriveMechanism {
    private final double MAX_POWER = 0.8;
    private final double DEADZONE = 0.1;

    private DcMotor[] motors = new DcMotor[4];

    public DriveMechanism(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        motors[0] = frontLeft;
        motors[1] = frontRight;
        motors[2] = backLeft;
        motors[3] = backRight;

        // motors[0].setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void drive(double leftPower, double rightPower) {
        if (Math.abs(leftPower) < DEADZONE) leftPower = 0;
        if (Math.abs(rightPower) < DEADZONE) rightPower = 0;

        leftPower *= MAX_POWER;
        rightPower *= MAX_POWER;

        for(int i = 0; i < motors.length; i++) {
            motors[i].setPower((i % 2 == 0) ? leftPower : rightPower);
        }
    }

    public void driveTimed(double leftPower, double rightPower, int millis) {
        drive(leftPower, rightPower);
        sleep(millis);
        drive(0, 0);
    }
}

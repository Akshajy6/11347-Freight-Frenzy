package io.github.circuitrunners.teamcode.submechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;

public class DriveMechanism {
    private final double MAX_POWER = 0.8;

    private DcMotor[] motors = new DcMotor[4];

    public DriveMechanism(DcMotor frontLeft, DcMotor frontRight, DcMotor backLeft, DcMotor backRight) {
        motors[0] = frontLeft;
        motors[1] = frontRight;
        motors[2] = backLeft;
        motors[3] = backRight;
    }

    public void drive(double leftPower, double rightPower) {
        leftPower *= MAX_POWER;
        rightPower *= MAX_POWER;

        for(int i = 0; i < motors.length; i++) {
            motors[i].setPower((i % 2 == 0) ? leftPower : rightPower);
        }
    }
}

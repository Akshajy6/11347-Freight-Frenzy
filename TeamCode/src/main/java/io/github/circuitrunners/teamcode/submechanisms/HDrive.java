package io.github.circuitrunners.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;

public class HDrive {

    private DcMotor motorFR, motorFL, motorBR, motorBL, motorM;

    public HDrive(DcMotor motorFR, DcMotor motorFL, DcMotor motorBR, DcMotor motorBL, DcMotor motorM) {
        this.motorFR = motorFR;
        this.motorFL = motorFL;
        this.motorBR = motorBR;
        this.motorBL = motorBL;
        this.motorM = motorM;

        this.motorFR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motorFL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motorBR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motorBL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.motorM.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public void drive(double x, double y, double r) {

        double leftPower = (Math.abs(y + r) <= 0.8) ? y + r : Math.signum(y + r) * 0.8;
        double rightPower = (Math.abs(y - r) <= 0.8) ? y - r : Math.signum(y - r) * 0.8;
        double centerPower = x * 0.8;

        this.motorFR.setPower(rightPower);
        this.motorFL.setPower(leftPower);
        this.motorBR.setPower(rightPower);
        this.motorBL.setPower(leftPower);
        this.motorM.setPower(centerPower);
    }
}
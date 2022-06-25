package io.github.circuitrunners.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import io.github.circuitrunners.subsystems.HDrive;

public class HTeleOp extends OpMode {
    HDrive drivetrain;

    @Override
    public void init() {
        drivetrain = new HDrive(hardwareMap.get(DcMotor.class, "motorfr"),
                hardwareMap.get(DcMotor.class, "motorfl"),
                hardwareMap.get(DcMotor.class, "motorbr"),
                hardwareMap.get(DcMotor.class, "motorbl"),
                hardwareMap.get(DcMotor.class, "motorm"));
    }

    @Override
    public void loop() {
        drivetrain.drive(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);
    }
}
package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import io.github.circuitrunners.teamcode.submechanisms.DriveMechanism;

public abstract class BaseAuto extends LinearOpMode {
    DriveMechanism drivebase;
    DcMotor carouselMech;

    @Override
    public void runOpMode() {
        drivebase = new DriveMechanism(hardwareMap.get(DcMotor.class, "frontleft"),
                hardwareMap.get(DcMotor.class, "frontright"),
                hardwareMap.get(DcMotor.class, "backleft"),
                hardwareMap.get(DcMotor.class, "backright"));

        carouselMech = hardwareMap.get(DcMotor.class, "carousel");

        waitForStart();
    }
}

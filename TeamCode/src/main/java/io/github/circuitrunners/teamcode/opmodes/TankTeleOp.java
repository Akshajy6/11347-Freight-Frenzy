package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import io.github.circuitrunners.teamcode.submechanisms.DriveMechanism;

@TeleOp
public class TankTeleOp extends OpMode {
    DriveMechanism drivebase;
    DcMotor carouselMech;
    
    @Override
    public void init() {

        drivebase = new DriveMechanism(hardwareMap.get(DcMotor.class, "frontleft"),
                hardwareMap.get(DcMotor.class, "frontright"),
                hardwareMap.get(DcMotor.class, "backleft"),
                hardwareMap.get(DcMotor.class, "backright"));

        carouselMech = hardwareMap.get(DcMotor.class, "carousel");
    }

    @Override
    public void loop() {
        double slowFactor = gamepad1.right_trigger * 0.8;
        drivebase.drive(gamepad1.left_stick_y - slowFactor, -gamepad1.right_stick_y - slowFactor);

        carouselMech.setPower(0.8 * (gamepad2.right_trigger - gamepad2.left_trigger));
    }
}

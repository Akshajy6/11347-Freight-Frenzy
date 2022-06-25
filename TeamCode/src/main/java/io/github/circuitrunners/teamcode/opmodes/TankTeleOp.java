package io.github.circuitrunners.teamcode.opmodes;

import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


import io.github.circuitrunners.teamcode.submechanisms.DriveMechanism;

@TeleOp
public class TankTeleOp extends OpMode {
    DriveMechanism drivebase;
    DcMotor carouselMech;
    DcMotor rbRoller;
    DcMotor extension;
    Servo dump;

    @Override
    public void init() {

        drivebase = new DriveMechanism(hardwareMap.get(DcMotor.class, "frontleft"),
                hardwareMap.get(DcMotor.class, "frontright"),
                hardwareMap.get(DcMotor.class, "backleft"),
                hardwareMap.get(DcMotor.class, "backright"));

        carouselMech = hardwareMap.get(DcMotor.class, "carousel");

        rbRoller = hardwareMap.get(DcMotor.class, "roller");

        extension = hardwareMap.get(DcMotor.class, "linslides");

        dump = hardwareMap.servo.get("dump");
        //dump.setPosition(0.4);
    }

    @Override
    public void loop() {

        drivebase.drive(gamepad1.left_stick_y, -gamepad1.right_stick_y);

        carouselMech.setPower(0.8 * (gamepad1.right_trigger - gamepad1.left_trigger));

        rbRoller.setPower(0.8 * (gamepad2.left_trigger - gamepad2.right_trigger)); //Power TBD

        extension.setPower(gamepad2.right_stick_y * 0.5);

        if(gamepad2.right_bumper)
        {
            dump.setPosition(0.8); //Position TBD

            dump.setPosition(0.4);

        }
    }
}

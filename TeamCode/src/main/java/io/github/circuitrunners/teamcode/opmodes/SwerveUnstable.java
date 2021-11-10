package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Rename when it's not a mess.
 */
@TeleOp
public class SwerveUnstable extends OpMode {
    static double SERVO_ZERO = 0.5;
    static double CONTROLLER_DEADZONE = 0.2;

    Servo[] servos;
    DcMotor[] motors;
    CRServo carouselMech;

    @Override
    public void init() {
        servos = new Servo[]{
                hardwareMap.get(Servo.class, "leftbackservo"),
                hardwareMap.get(Servo.class, "rightbackservo"),
                hardwareMap.get(Servo.class, "leftfrontservo"),
                hardwareMap.get(Servo.class, "rightfrontservo")
        };

        motors = new DcMotor[]{
                hardwareMap.get(DcMotor.class, "leftbackmotor"),
                hardwareMap.get(DcMotor.class, "rightbackmotor"),
                hardwareMap.get(DcMotor.class, "leftfrontmotor"),
                hardwareMap.get(DcMotor.class, "rightfrontmotor"),
        };

        carouselMech = hardwareMap.get(CRServo.class, "carouselservo");
        carouselMech.setDirection(DcMotorSimple.Direction.FORWARD);

        servos[1].setPosition(2.0/3);
        servos[2].setPosition(2.0/3);

        servos[0].setPosition(1.0/3);
        servos[3].setPosition(1.0/3);
    }

    @Override
    public void loop() {
        double power;
        double rot_power;
        double heading;

        power = Math.hypot(gamepad1.left_stick_x, gamepad1.left_stick_y);
        if (power < CONTROLLER_DEADZONE) power = 0;
        rot_power = (gamepad1.right_stick_x > CONTROLLER_DEADZONE) ? gamepad1.right_stick_x : 0;

        if (power == 0 && rot_power == 0);
        else if (power > rot_power) {
            heading = Math.atan2(-gamepad1.left_stick_x, gamepad1.left_stick_y);
            if (heading > 3 * Math.PI / 4 && heading < 5 * Math.PI / 4) heading -= Math.PI;

            heading /= (Math.PI * 3 / 2);
            heading += SERVO_ZERO;

            for (Servo s : servos) s.setPosition(heading);
            for (DcMotor m : motors) m.setPower(power);
        }
        else {
            servos[1].setPosition(2.0/3); // back right
            servos[2].setPosition(2.0/3); // front left

            servos[0].setPosition(1.0/3); // back left
            servos[3].setPosition(1.0/3); // front right

            motors[0].setPower(rot_power);
            motors[2].setPower(rot_power);

            motors[1].setPower(-rot_power);
            motors[3].setPower(-rot_power);
        }
    }
}

package io.github.circuitrunners.teamcode.submechanisms;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Please don't use this one juuuust yet. We haven't built the actual mechanism so this will basically be useless. I'm just
 * writing the code now rather than later.
 */
public class ClawMechanism {
    Servo grabServo;
    Servo clawServo;
    Servo liftServo;


    public ClawMechanism(Servo grabServo, Servo clawServo, Servo liftServo) {
        this.grabServo = grabServo;
        this.clawServo = clawServo;
        this.liftServo = liftServo;
    }
}

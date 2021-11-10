package io.github.circuitrunners.teamcode.swerveutils;

public class SwerveDrive {

    private SwerveWheel leftFront, leftBack, rightFront, rightBack;

    private static double DEAD_ZONE = 0.2; // If the left stick's magnitude is below this then it should be treated as if the left stick is not being moved at all

    public SwerveDrive(SwerveWheel aLeftFront, SwerveWheel aLeftBack, SwerveWheel aRightFront, SwerveWheel aRightBack) {
        leftFront = aLeftFront;
        leftBack = aLeftBack;
        rightFront = aRightFront;
        rightBack = aRightBack;
    }

    public void driveSwerve(double x, double y, double rot) {
        if (Math.hypot(y, x) <= 0.2) {
            x = 0;
            y = 0;
        }

        if (rot <= 0.2) {
            rot = 0;
        }

        if (x == 0 && y == 0 && rot == 0); // does absolutely nothing, takes first precedence
        else if (Math.hypot(y, x) > rot) { // sets the robot to translate
            leftFront.setMovementRect(x, y);
            leftBack.setMovementRect(x, y);
            rightFront.setMovementRect(x, y);
            rightBack.setMovementRect(x, y);
        }
        else { // sets the robot to rotate
            leftFront.setMovementRadial(Math.PI / 4, rot, true);
            leftBack.setMovementRadial(3 * Math.PI / 4, rot, true);
            rightFront.setMovementRadial(3 * Math.PI / 4, -rot, true);
            rightBack.setMovementRadial(Math.PI / 4, -rot, true);
        }
    }

    public void driveSwerve(double x, double y, double rot, boolean moving) {
        if (Math.hypot(y, x) <= 0.2) {
            x = 0;
            y = 0;
        }

        if (Math.hypot(y, x) > rot) {
            if (!moving) {
                leftFront.setMovementRadial(Math.atan2(y, x), 0);
                leftBack.setMovementRadial(Math.atan2(y, x), 0);
                rightFront.setMovementRadial(Math.atan2(y, x), 0);
                rightBack.setMovementRadial(Math.atan2(y, x), 0);
            }
            else {
                leftFront.setMovementRect(x, y);
                leftBack.setMovementRect(x, y);
                rightFront.setMovementRect(x, y);
                rightBack.setMovementRect(x, y);
            }
        }
        else {
            leftFront.setMovementRadial(Math.PI / 4, rot, true);
            leftBack.setMovementRadial(3 * Math.PI / 4, rot, true);
            rightFront.setMovementRadial(3 * Math.PI / 4, -rot, true);
            rightBack.setMovementRadial(Math.PI / 4, -rot, true);
        }
    }
}
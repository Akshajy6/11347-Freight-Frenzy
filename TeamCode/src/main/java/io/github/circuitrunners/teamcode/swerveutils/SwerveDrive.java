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

    public void driveSwerve(double x, double y, double rot, boolean moving) {
        if (Math.hypot(y, x) <= 0.2) {
            x = 0;
            y = 0;
        }

        if (Math.hypot(y, x) > rot) {
            if (!moving) {
//                leftFront.setMovementRadial();
//                leftBack.setMovementRadial();
//                rightFront.setMovementRadial();
//                rightBack.setMovementRadial();
            }
            else {
                leftFront.setMovementRect(x, y);
                leftBack.setMovementRect(x, y);
                rightFront.setMovementRect(x, y);
                rightBack.setMovementRect(x, y);
            }
        }
        else {
            leftFront.forceDirectionRadial(Math.PI / 4, rot);
            leftBack.forceDirectionRadial(3 * Math.PI / 4, rot);
            rightFront.forceDirectionRadial(3 * Math.PI / 4, -rot);
            rightBack.forceDirectionRadial(Math.PI / 4, -rot);
        }
    }
}
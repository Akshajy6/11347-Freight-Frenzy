package io.github.circuitrunners.teamcode.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;


@Autonomous
public class BlueCarouselAuto extends BaseAuto {
    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        drive.swankDrive(1, 1);
        sleep(1000);
        drive.swankDrive(0, 0);
        carouselMech.setPower(-1);
        sleep(3000);
        carouselMech.setPower(0);
    }

}

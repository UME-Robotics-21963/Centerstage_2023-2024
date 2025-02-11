package org.firstinspires.ftc.teamcode.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HWmap;

//temp blue far side!
@Autonomous(name = "blue farside1",group = "roboto")
public class UGHHHHH extends LinearOpMode {
    HWmap robot = new HWmap();
    private final ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode()
    {
       robot.initialize(hardwareMap);
       waitForStart();
        // to turn right, make direction = 1 to turn left, make direction = -1,
        // to go forward make direction = 0 to go in reverse make direction = -2

        turnSeconds(1,.5
                ,-1);
        turnSeconds(2.25,.75,0);//?
        telemetry.addLine("happy dance!");
    }
    public void turnSeconds(double seconds, double speed, double direction) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() <= seconds)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            if (true) {
                if (direction == -1) {
                    robot.FLDrive.setPower(speed);
                    robot.FRDrive.setPower(-speed);
                    robot.BLDrive.setPower(speed);
                    robot.BRDrive.setPower(-speed);
                }
                if (direction == 0) {
                    robot.FLDrive.setPower(speed);
                    robot.FRDrive.setPower(speed);
                    robot.BLDrive.setPower(speed);
                    robot.BRDrive.setPower(speed);
                }
                if (direction == -2) {
                    robot.FLDrive.setPower(-speed);
                    robot.FRDrive.setPower(-speed);
                    robot.BLDrive.setPower(-speed);
                    robot.BRDrive.setPower(-speed);
                }
                if (direction == 1) {
                    robot.FLDrive.setPower(-speed);
                    robot.FRDrive.setPower(speed);
                    robot.BLDrive.setPower(-speed);
                    robot.BRDrive.setPower(speed);
                }
                robot.FLDrive.setPower(0);
                robot.FRDrive.setPower(0);
                robot.BLDrive.setPower(0);
                robot.BRDrive.setPower(0);
            }
        }// to turn right, make direction = 1 to turn left, make direction = -1,
        // to go forward make direction = 0 to go in reverse make direction = -2

    }
}

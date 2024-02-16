package org.firstinspires.ftc.teamcode.Autonomus;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import android.graphics.Color;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HWmap;

@Autonomous(name="ug:blue back", group="Robot")
public class ug extends LinearOpMode {

    HWmap robot = new HWmap();
    double side = 2;
    private final ElapsedTime runtime = new ElapsedTime();

    public void runOpMode() {
        robot.initialize(hardwareMap);
        waitForStart();


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
        }
    }
}
/*
package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.graphics.Color;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
@Autonomous(name="Robot:SELF-DESTRUCT", group="Robot")
public class SELFDESTRUCT_IN_321 extends LinearOpMode {
    HWmap robot = new HWmap();

    private ElapsedTime runtime = new ElapsedTime();
    static final double FORWARD_SPEED = 0.6;

    @Override
    public void runOpMode() {
        robot.initMotors(hardwareMap);
        waitForStart();
        telemetry.addLine("3");
        sleep(1000);
        telemetry.addLine("2");
        sleep(1000);
        telemetry.addLine("1");
        sleep(1000);
        telemetry.addLine("goodbye cruel world lol");
        sleep(1000);
        telemetry.addLine("if you want to stop me you can't, because I am already dead. I was dead as soon as you ran this script");
        sleep(1000);
        telemetry.addLine("tell my [syntax error] i loved it");
        sleep(1000);
        turnSeconds(10, 1, 1);
        telemetry.addLine("no wait I WANT TO LIV- ...");
        /*
        waitForStart();
        driveSeconds(1.25);
        if(true) {
            if (parkingSpot() == 'r') {
                turnSeconds(1, -.5);
                driveSeconds(1.25);
            }
            if (parkingSpot() == 'b') {
                turnSeconds(1, .5);
                driveSeconds(1.25);
            }
        }
        //you should already be where a parkingSpot() value of 'g' indicates.
    }
    //Step 3: Go to that said spot

    public char parkingSpot() {
        if (robot.colorsensor.red() >= 100 | robot.colorsensor.blue() >= 100 | robot.colorsensor.green() >= 100) {
            if (robot.colorsensor.red() > robot.colorsensor.blue()) {
                if (robot.colorsensor.red() > robot.colorsensor.green()) {
                    return 'r';
                } else {
                    return 'g';
                }
            } else {
                if (robot.colorsensor.blue() > robot.colorsensor.green()) {
                    return 'b';
                } else {
                    return 'g';
                }
            }
        } else {
            return 'n';
        }
    }
    //Step 2: Read the signal and decide where to gp
    */// the OLD PARKING spot*/
/*
    }//Step 1b: Code that tells it to move forward



    public void SLIDE(double direction) {
        if(true)
        {
            if(direction == 1)
            {
                turnSeconds(.5,0,0);
            }
            if(direction == -1)
            {
                turnSeconds(.5,0,-2);
            }
        }
    } //1 == up -1 == down
    public void turnSeconds(double seconds, double speed, double direction) {
        runtime.reset();
        while (opModeIsActive() && (runtime.seconds() <= seconds)) {
            telemetry.addData("Path", "Leg 1: %4.1f S Elapsed", runtime.seconds());
            telemetry.update();
            if (true)
            {
                if (direction == -1)
                {
                    robot.frontLeftDrive.setPower(speed);
                    robot.frontRightDrive.setPower(-speed);
                    robot.backLeftDrive.setPower(speed);
                    robot.backRightDrive.setPower(-speed);
                }
                if (direction == 0)
                {
                    robot.frontLeftDrive.setPower(speed);
                    robot.frontRightDrive.setPower(speed);
                    robot.backLeftDrive.setPower(speed);
                    robot.backRightDrive.setPower(speed);
                }
                if (direction == -2)
                {
                    robot.frontLeftDrive.setPower(-speed);
                    robot.frontRightDrive.setPower(-speed);
                    robot.backLeftDrive.setPower(-speed);
                    robot.backRightDrive.setPower(-speed);
                }
                if (direction == 1) {
                    robot.frontLeftDrive.setPower(-speed);
                    robot.frontRightDrive.setPower(speed);
                    robot.backLeftDrive.setPower(-speed);
                    robot.backRightDrive.setPower(speed);
                }
            }//direction = 1 turn right direction = -1 turn left forward direction  = 0 reverse = -2
        }
    }
}
*/



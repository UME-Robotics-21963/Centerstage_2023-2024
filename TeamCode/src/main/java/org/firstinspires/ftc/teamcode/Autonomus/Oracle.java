package org.firstinspires.ftc.teamcode.Autonomus;

import android.graphics.Color;


import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.HWmap;

//for side closest to board

@Autonomous(name="Robot:Oracle:Red", group="Robot")
public class Oracle extends LinearOpMode {
    HWmap robot = new HWmap();
    double side = 99;
    private final ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        robot.initialize(hardwareMap);
        waitForStart();

    /*
            while((!colorsense().equals("red")) && opModeIsActive()){

            if(colorsense().equals("red"))
                {
                    break;
                }
            }
    */
        // to turn right, make direction = 1 to turn left, make direction = -1,
        // to go forward make direction = 0 to go in reverse make direction = -2
        turnSeconds(.3,.75,1);
        sleep(500);
        turnSeconds(.5,.75,0);
        sleep(500);
        turnSeconds(.2,.75,-1);
        sleep(500);
        turnSeconds(1.5, .75, 0);// 6,4 to 6,6
        sleep(500);
        turnSeconds(.2, .75, -2);// 6,6 to 6,4
        sleep(500);
        turnSeconds(.2,.75,0);
        while (side == -1)
        {
            RGB();
            if (side == 0 || side ==1)
            {
                break;
            }
        }




        /*
        sleep(500);
        turnSeconds(1.75, .5, -2);
        sleep(500);
        turnSeconds(.75, .5, 1);
        sleep(500);
        turnSeconds(6.75, .5, 0);
        sleep(500);
        turnSeconds(.75, .5, -1);
        sleep(500);
        turnSeconds(6.75, .5, 0);
        sleep(500);
        */
//Our robot goes 6.6 inches every 1 second
//sleep(5000);
//turnSeconds(.5, -.5, 0);
        /*while (opModeIsActive()) {
            if(colorsense().equals("purple"))
            {
                telemetry.addLine("i found purple!");
            }

            if(colorsense().equals("yellow"))
            {
                telemetry.addLine("i found yellow!");
            }

            if(colorsense().equals("green"))
            {
                telemetry.addLine("i found green!");
            }
            if(colorsense().equals("white"))
            {
                telemetry.addLine("i found white!");
            }
            if(colorsense().equals("none/floor"))
            {
                telemetry.addLine("i found the floor... or something like that");
            }

            telemetry.update();
            sleep(1000);
        }
*/
    }


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
    */// code in closet


    public void RGB ()
    {
        robot.colors= robot.colorsensor.getNormalizedColors();
        Color.colorToHSV(robot.colors.toColor(), robot.hsvValues);
        if(robot.hsvValues[0]> 0 || robot.hsvValues[1]>0 || robot.hsvValues[2]>0)
        {
            if(robot.hsvValues[0] != 180)
            {
                if(robot.colors.blue > robot.colors.red && robot.colors.blue > robot.colors.green)
                {
                    side = 1;
                }
                if(  robot.colors.red > robot.colors.blue && robot.colors.red > robot.colors.green)
                {
                    side = 2;
                }
            }
        }
    }

    public String colorsense()
    {
        robot.colors = robot.colorsensor.getNormalizedColors();
        Color.colorToHSV(robot.colors.toColor(), robot.hsvValues);
        if (robot.hsvValues[0] > 0 || robot.hsvValues[1] > 0 || robot.hsvValues[2] > 0) {
            if(robot.hsvValues[0] != 180) {
                if (robot.colors.blue > robot.colors.red && robot.colors.green > robot.colors.red && robot.colors.green > robot.colors.blue) {
                    return "green";
                }
                if (robot.colors.blue > robot.colors.red && robot.colors.blue > robot.colors.green && robot.colors.green > robot.colors.red) {
                    return "purple";
                }
                if (robot.colors.red > robot.colors.blue && robot.colors.green > robot.colors.blue && robot.colors.green > robot.colors.red) {
                    return "yellow";
                }//rgb(255,254,253)
            }
                return "white";
        }

        return "none/floor";

    }
    public void Phase()  // side = 1 blue team,  side = 2 red team; phase: do this and increment phase up repeat with the next phase up
    {
        // to turn right, make direction = -1 to turn left, make direction = 1,
        // to go forward make direction = 0 to go in reverse make direction = -2
        double phaseNumber = 0;
        if(phaseNumber == 0) {
            turnSeconds(1.75, .5, 0);// 6,4 to 6,6
            turnSeconds(.5, .5, -2);
            turnSeconds(.5, .5, 0);
            sleep(1000);
            phaseNumber = 1;
        }
            if (side == 1) {
                phaseNumber = 1;
                sleep(1000);
                if (phaseNumber == 1 && side == 1)//phase 1, side 1
                {
                    turnSeconds(.5, .5, -1);
                    turnSeconds(1, .5, 0);
                    telemetry.addLine("lol cant do stuff");
                    phaseNumber = 2;
                }
            }
            if (side == 2) {
                phaseNumber = 1;
                sleep(1000);
                if (phaseNumber == 1 && side == 2)//phase 1, side 2
                {
                    turnSeconds(.5,.5,1);
                    turnSeconds(1,.5,0);
                    telemetry.addLine("lol cant do stuff");
                    phaseNumber = 2;
                }
            }
        }






    public void CLAW(double clawPosition, double saystuff)
    {

        if (saystuff == 0) {
            telemetry.addLine(
                    "THE CLAW... " +
                            "the claw is our master..." +
                            "The claw chooses who will go and who will stay." + robot.servo.getPower() + " I have been chosen!! Farewell, my friends!  I go on to a better place."
            );
            telemetry.update();
        }// full sentence
        else {

            telemetry.addData("Claw:",robot.servo.getPower());
        }//just the important stuff

        if(clawPosition == 1) {
            robot.servo.setPower(0);
        }
        if(clawPosition == 2);{
            robot.servo.setPower(-0.1);
        }
    }
    public void armMove(double direc, double speed)
    {
        if(direc == 0 )
        {
            robot.inTakeMotor.setPower(speed);
        }
        if(direc == 1 )
        {
            robot.inTakeMotor.setPower(-speed);
        }
    } // 0 == up 1 == down

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
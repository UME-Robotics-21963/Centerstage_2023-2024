/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="Namean ", group="Linear Opmode")
public class Namean extends LinearOpMode {

    HWmap robot = new HWmap();
    double frontleftPower, backleftPower, frontrightPower, backrightPower, drive, turn, strafe;

    @Override
    public void runOpMode() {
        robot.initialize(hardwareMap);
        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        if (true) {
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                if (true) {
                    // POV Mode uses left stick to go forward and strafe, and right stick to turn.
                    if (true) {
                        drive = -gamepad1.left_stick_y;
                        turn = gamepad1.right_stick_x;
                        strafe = gamepad1.left_stick_x;
                        //sets drive, turn, or strafe to 0 if they're close enough to 0 (difference of less than 0.1)
                        if (Math.abs(drive) <= .1) {
                            drive = 0;
                        }
                        if (Math.abs(turn) <= .1) {
                            turn = 0;
                        }
                        if (Math.abs(strafe) <= .1) {
                            strafe = 0;
                        }
                    }//sets drive, strafe, and turn variables
                    if (true) {
                        frontleftPower = drive + turn - strafe;
                        backrightPower = drive - turn - strafe;
                        frontrightPower = drive - turn + strafe;
                        backleftPower = drive + turn + strafe;
                    }//turns drive, strafe, and turn variables into motor variables
                    if (true) {
                        double max = Math.max(
                                Math.max(Math.abs(frontleftPower), Math.abs(frontrightPower)),
                                Math.max(Math.abs(backleftPower), Math.abs(backrightPower))
                        );
                        if (max > 1) {
                            backleftPower /= max;
                            backrightPower /= max;
                            frontleftPower /= max;
                            frontrightPower /= max;
                        }
                    }//This code makes sure that the motors' powers stay under 1 (the limit) while staying proportional to each other.
                /*
                This requires no math, but it is hard to drive forward slowly and keep straight.
                leftPower  = -gamepad1.left_stick_y;
                rightPower = -gamepad1.right_stick_y;
                *///tonk mode
                    if (true) {
                        robot.BLDrive.setPower(backleftPower);
                        robot.BRDrive.setPower(backrightPower);
                        robot.FRDrive.setPower(frontrightPower);
                        robot.FLDrive.setPower(frontleftPower);
                    } //Send calculated power to wheels
                }//chassis motors
            /*
            //linear slide code: if y is pressed, go up, if a is pressed, go down, if neither is pressed, stay still
            if(gamepad1.y | gamepad1.a)
            {
                if(gamepad1.y)
                {
                    slide.setPower(0.5);
                }
                else
                {
                    slide.setPower(-0.5);
                }
            }
            else

            {
                slide.setPower(0);
                slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            }
           */ //linear slide code
            /* //claw closing & opening code

            if (gamepad1.x) {
                robot.lClaw.setPosition(0.1);
            }
            if (gamepad1.b) {
                robot.lClaw.setPosition(0);
            }

            Show the elapsed game time and wheel power.
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", leftBackdrive , );
            telemetry.update()
            */ //claw code

            }
        }
    }
}




//look at the bottom
package org.firstinspires.ftc.teamcode;



import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the Teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When a selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 * //MARCUS YA CODE BORK
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 * Use Android Studio to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */
//todo"duck"
    //don't you DARE delete todo DUCK or i will hurt you
@TeleOp(name="BORK", group="Linear Opmode")
public class BORK extends LinearOpMode {
    HWmap robot = new HWmap();
    ElapsedTime inTakeTimer = new ElapsedTime();
    @Override
    public void runOpMode() {
        robot.initialize(hardwareMap);
        robot.Telementry();
        robot.servo.setPower(1);
        waitForStart();
        //telemetry.addData("byarharhar", robot.servo.getPower());

        if (true) {
            // run until the end of the match (driver presses STOP)
            while (opModeIsActive()) {
                if (true){
                    // POV Mode uses left stick to go forward and strafe, and right stick to turn.
                    if (true) {
                        robot.drive = gamepad1.left_stick_y;
                        robot.turn = gamepad1.right_stick_x;
                        robot.strafe = gamepad1.left_stick_x;
                        //sets drive, turn, or strafe to 0 if they're close enough to 0 (difference of less than 0.1)
                        if (Math.abs(robot.drive) <= 0.1) {
                            robot.drive = 0;
                        }
                        if (Math.abs(robot.turn) <= 0.1) {
                            robot.turn = 0;
                        }
                        if (Math.abs(robot.strafe) <= 0.1) {
                            robot.strafe = 0;
                        }
                    }//sets drive, strafe, and turn variables
                    if (true) {
                        robot.FLPower = robot.drive + robot.turn + robot.strafe;
                        robot.BLPower = robot.drive + robot.turn - robot.strafe;
                        robot.FRPower = robot.drive - robot.turn - robot.strafe;
                        robot.BRpower = robot.drive - robot.turn + robot.strafe;
                    }//turns drive, strafe, and turn variables into motor variables
                    if (true) {
                        double max = Math.max(
                                Math.max(Math.abs(robot.FLPower),/* > or < */ Math.abs(robot.FRPower)),
                                Math.max(Math.abs(robot.BLPower),/* > or < */ Math.abs(robot.BRpower))
                        );
                        if (max > 1) {
                            robot.BLPower /= max;
                            robot.BRpower /= max;
                            robot.FLPower /= max;
                            robot.FRPower /= max;
                        }

                    }//This code makes sure that the motors' powers stay under 1 (the limit) while staying proportional to each other.

                /*
                This requires no math, but it is hard to drive forward slowly and keep straight.
                leftPower  = -gamepad1.left_stick_y;
                rightPower = -gamepad1.right_stick_y;
                *///tonk mode

                    if (true) {
                        if (Math.abs(robot.BLPower) < Math.abs(robot.BLDrive.getPower())) {
                            if (Math.abs((robot.BLPower + robot.BLDrive.getPower()) * .5) <= 0.1) {
                                robot.BLDrive.setPower((robot.BLPower + robot.BLDrive.getPower()) * .5);
                            } else {
                                robot.BLDrive.setPower(0);
                            }
                        } else {
                            robot.BLDrive.setPower(robot.BLPower / 1.5);
                        }

                        if (Math.abs(robot.BRpower) < Math.abs(robot.BRDrive.getPower())) {
                            if (Math.abs((robot.BRpower + robot.BRDrive.getPower()) * .5) <= 0.1) {
                                robot.BRDrive.setPower((robot.BRpower + robot.BRDrive.getPower()) * .5);
                            } else {
                                robot.BRDrive.setPower(0);
                            }
                        } else {
                            robot.BRDrive.setPower(robot.BRpower / 1.5);
                        }

                        if (Math.abs(robot.FLPower) < Math.abs(robot.FLDrive.getPower())) {
                            if (Math.abs((robot.FLPower + robot.FLDrive.getPower()) * .5) <= 0.1) {
                                robot.FLDrive.setPower((robot.FLPower + robot.FLDrive.getPower()) * .5);
                            } else {
                                robot.FLDrive.setPower(0);
                            }
                        } else {
                            robot.FLDrive.setPower(robot.FLPower / 1.5);
                        }

                        if (Math.abs(robot.FRPower) < Math.abs(robot.FRDrive.getPower())) {
                            if (Math.abs((robot.FRPower + robot.FRDrive.getPower()) * .5) <= 0.1) {
                                robot.FRDrive.setPower((robot.FRPower + robot.FRDrive.getPower()) * .5);
                            } else {
                                robot.FRDrive.setPower(0);
                            }
                        } else {
                            robot.FRDrive.setPower(robot.FRPower / 1.5);
                        }

                    } //Send calculated power to wheels

                }//chassis motors

                //linear slide code: if up dpad is pressed, go up, if down dpad is pressed, go down, if neither is pressed, stay still
                /*
                if (gamepad2.dpad_up | gamepad2.dpad_down) {
                    if (gamepad2.dpad_up) {
                        robot.slide.setPower(-0.6);
                    } else {
                        robot.slide.setPower(0.6);
                    }

                } else {
                    robot.slide.setPower(0);
                    robot.slide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

                }
                */

                if(gamepad2.dpad_down)//if gamepad 1 down
                {
                    if(!robot.toggleInputs[0][0]) {

                        robot.toggleInputs[0][0] = true;
                        robot.toggleInputs[0][1] = !robot.toggleInputs[0][1];
                        inTakeTimer.reset();
                    }
                }else robot.toggleInputs[0][0] = false;

                if(!gamepad2.dpad_down) {

                    if (robot.toggleInputs[0][1]) {

                        robot.inTakeMotor.setPower(-1);
                    } else {

                        robot.inTakeMotor.setPower(0);
                    }
                }

                if (gamepad2.x) {
                    robot.servo.setPower(1);
                } else if (gamepad2.b) {
                    robot.servo.setPower(0);
                } //claw closing & opening code

                if(gamepad2.y)
                {
                    robot.plane.setPower(0);
                } else {
                    robot.plane.setPower(1);
                }

                if(robot.plane.getPower() == 1)
                {
                    telemetry.addLine("drone launched!");
                }
                //telemetry code all in one place
            }

        }
    }
}
//look at the top
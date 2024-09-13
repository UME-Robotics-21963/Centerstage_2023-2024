package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

@Autonomous(name="IMU test", group="Robot")
public class ImuTest extends LinearOpMode {
    private double  targetHeading = 0, headingError  = 0;
    static final double P_TURN_GAIN  = 0.02, P_DRIVE_GAIN = 0.03;
    static final double TURN_SPEED = 0.2,  HEADING_THRESHOLD = 1.0, DRIVE_SPEED = 0.4;
    private double  driveSpeed    = 0;
    private double  turnSpeed     = 0;
    private double  leftSpeed     = 0;
    private double  rightSpeed    = 0;
    private int     leftTarget    = 0;
    private int     rightTarget   = 0;
    static final double     COUNTS_PER_MOTOR_REV    = 537.7 ;   // eg: GoBILDA 312 RPM Yellow Jacket
    static final double     DRIVE_GEAR_REDUCTION    = 1.0 ;     // No External Gearing.
    static final double     WHEEL_DIAMETER_INCHES   = 4.0 ;     // For figuring circumference
    static final double     COUNTS_PER_INCH         = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.1415);
    
   
    Orientation angles;
    double heading;
    HWmap robot = new HWmap();

    @Override
    public void runOpMode() {


            robot.initialize(hardwareMap);
            InitImu();
            robot.imu.resetYaw();

        waitForStart();
        while(opModeIsActive()){
        }
        //driveStraight(.5,2,90);

       // turnToHeading(.5,0);
        }

    public void InitImu() {
        robot.meters = new BNO055IMU.Parameters();
        RevHubOrientationOnRobot.LogoFacingDirection logoDirection = RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        RevHubOrientationOnRobot.UsbFacingDirection  usbDirection  = RevHubOrientationOnRobot.UsbFacingDirection.UP;
        RevHubOrientationOnRobot orientationOnRobot = new RevHubOrientationOnRobot(logoDirection, usbDirection);
        robot.imu.initialize(new IMU.Parameters(orientationOnRobot));

    }
    public void driveStraight(double maxDriveSpeed, double distance, double heading) {

        // Ensure that the OpMode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            int moveCounts = (int)(distance * COUNTS_PER_INCH);
            leftTarget = robot.FLDrive.getCurrentPosition() + moveCounts;
            rightTarget = robot.FRDrive.getCurrentPosition() + moveCounts;

            // Set Target FIRST, then turn on RUN_TO_POSITION
            robot.FLDrive.setTargetPosition(leftTarget);
            robot.BLDrive.setTargetPosition(leftTarget);
            robot.FRDrive.setTargetPosition(rightTarget);
            robot.BRDrive.setTargetPosition(rightTarget);

            robot.FLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.FRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.BLDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            robot.BRDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // Set the required driving speed  (must be positive for RUN_TO_POSITION)
            // Start driving straight, and then enter the control loop
            maxDriveSpeed = Math.abs(maxDriveSpeed);
            moveRobot(maxDriveSpeed, 0);

            // keep looping while we are still active, and BOTH motors are running.
            while (opModeIsActive() &&
                    (robot.FLDrive.isBusy() && robot.FRDrive.isBusy())) {
                // Determine required steering to keep on heading
                turnSpeed = getSteeringCorrection(heading, P_DRIVE_GAIN);

                // if driving in reverse, the motor correction also needs to be reversed
                if (distance < 0)
                    turnSpeed *= -1.0;

                // Apply the turning correction to the current driving speed.
                  moveRobot(driveSpeed, turnSpeed);

                // Display drive status for the driver.
                sendTelemetry(true);
            }

            // Stop all motion & Turn off RUN_TO_POSITION
            moveRobot(0, 0);
            robot.FLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.FRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.BLDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            robot.BRDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }
    public void moveRobot(double drive, double turn) {
        driveSpeed = drive;     // save this value as a class member so it can be used by telemetry.
        turnSpeed  = turn;      // save this value as a class member so it can be used by telemetry.

        leftSpeed  = drive - turn;
        rightSpeed = drive + turn;

        // Scale speeds down if either one exceeds +/- 1.0;
        double max = Math.max(Math.abs(leftSpeed), Math.abs(rightSpeed));
        if (max > 1.0)
        {
            leftSpeed /= max;
            rightSpeed /= max;
        }

        robot.FLDrive.setPower(leftSpeed);
        robot.BLDrive.setPower(leftSpeed);
        robot.FRDrive.setPower(rightSpeed);
        robot.BRDrive.setPower(rightSpeed);
    }
    public void turnToHeading(double maxTurnSpeed, double heading) {

        // Run getSteeringCorrection() once to pre-calculate the current error
        getSteeringCorrection(heading, P_DRIVE_GAIN);

        // keep looping while we are still active, and not on heading.
        while (opModeIsActive() && (Math.abs(headingError) > HEADING_THRESHOLD)) {

            // Determine required steering to keep on heading
            turnSpeed = getSteeringCorrection(heading, P_TURN_GAIN);

            // Clip the speed to the maximum permitted value.
            turnSpeed = Range.clip(turnSpeed, -maxTurnSpeed, maxTurnSpeed);

            // Pivot in place by applying the turning correction
            moveRobot(0, turnSpeed);

            // Display drive status for the driver.
            sendTelemetry(false);
        }

        // Stop all motion;
        moveRobot(0, 0);
    }
    private void sendTelemetry(boolean straight) {

        if (straight) {
            telemetry.addData("Motion", "Drive Straight");
            telemetry.addData("Target Pos L:R",  "%7d:%7d",      leftTarget,  rightTarget);
            telemetry.addData("Actual Pos L:R",  "%7d:%7d",      robot.FLDrive.getCurrentPosition(),
                    robot.FRDrive.getCurrentPosition(), robot.BLDrive.getCurrentPosition(),robot.BRDrive.getCurrentPosition());

        } else {
            telemetry.addData("Motion", "Turning");
        }

        telemetry.addData("Heading- Target : Current", "%5.2f : %5.0f", targetHeading, getHeading());
        telemetry.addData("Error  : Steer Pwr",  "%5.1f : %5.1f", headingError, turnSpeed);
        telemetry.addData("Wheel Speeds L : R", "%5.2f : %5.2f", leftSpeed, rightSpeed);
        telemetry.update();
    }

    public void driveHeading(double turn, double target, double timeout) {
        ElapsedTime timer = new ElapsedTime();
        timer.reset();
        while(timer.seconds() > timeout) {
             {
                // double turn = (heading-target) * 0.02;
                heading = getHeading() + 180;
                double direction =  target - heading ;
               // robot.backrobot.FLDrive.setPower(turn * direction);
               // robot.backrobot.FRDrive.setPower(-turn * direction);
               // robot.frontrobot.FLDrive.setPower(turn * direction);
               // robot.frontrobot.FRDrive.setPower(-turn * direction);
                 if (true) {
                     if (Math.abs(turn * direction) < Math.abs(robot.BLDrive.getPower())) {
                         if (Math.abs((robot.BLPower + robot.BLDrive.getPower()) * .5) <= 0.1) {
                             robot.BLDrive.setPower((turn * direction + robot.BLDrive.getPower()) * .5);
                         } else {
                             robot.BLDrive.setPower(0);
                         }
                     } else {
                         robot.BLDrive.setPower(robot.BLPower);
                     }

                     if (Math.abs(-turn * direction) < Math.abs(robot.BRDrive.getPower())) {
                         if (Math.abs((robot.BRpower + robot.BRDrive.getPower()) * .5) <= 0.1) {
                             robot.BRDrive.setPower((-turn * direction + robot.BRDrive.getPower()) * .5);
                         } else {
                             robot.BRDrive.setPower(0);
                         }
                     } else {
                         robot.BRDrive.setPower(robot.BRpower);
                     }

                     if (Math.abs(turn * direction) < Math.abs(robot.FLDrive.getPower())) {
                         if (Math.abs((robot.FLPower + robot.FLDrive.getPower()) * .5) <= 0.1) {
                             robot.FLDrive.setPower((turn * direction + robot.FLDrive.getPower()) * .5);
                         } else {
                             robot.FLDrive.setPower(0);
                         }
                     } else {
                         robot.FLDrive.setPower(robot.FLPower);
                     }

                     if (Math.abs(-turn * direction) < Math.abs(robot.FRDrive.getPower())) {
                         if (Math.abs((robot.FRPower + robot.FRDrive.getPower()) * .5) <= 0.1) {
                             robot.FRDrive.setPower((-turn * direction + robot.FRDrive.getPower()) * .5);
                         } else {
                             robot.FRDrive.setPower(0);
                         }
                     } else {
                         robot.FRDrive.setPower(robot.FRPower);
                     }


                 } //Send calculated power to wheels
            }
        }
    }

    public double getSteeringCorrection(double desiredHeading, double proportionalGain) {
        targetHeading = desiredHeading;  // Save for telemetry

        // Determine the heading current error
        headingError = targetHeading - getHeading();

        // Normalize the error to be within +/- 180 degrees
        while (headingError > 180)  headingError -= 360;
        while (headingError <= -180) headingError += 360;

        // Multiply the error by the gain to determine the required steering correction/  Limit the result to +/- 1.0
        return Range.clip(headingError * proportionalGain, -1, 1);
    }
    double getHeading() {
        YawPitchRollAngles orientation = robot.imu.getRobotYawPitchRollAngles();
        return orientation.getRoll(AngleUnit.DEGREES);
    }
}

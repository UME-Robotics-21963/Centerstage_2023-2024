package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Hardwaretests.Motors_Hardware;

@Autonomous(name = "donotusepls",group = "roboto")
public class PlaceHoldertext extends LinearOpMode {
    HWmap robot = new HWmap();
    private final ElapsedTime runtime = new ElapsedTime();

    public DcMotor[][] Motors = new DcMotor[2][2];

    @Override
    public void runOpMode() {
        robot.initialize2(hardwareMap);
        Motors_Hardware.initMotors(hardwareMap);
        if(true) {
            Motors[0][0] = Motors_Hardware.motors[0];
            Motors[0][1] = Motors_Hardware.motors[1];
            Motors[1][0] = Motors_Hardware.motors[2];
            Motors[1][1] = Motors_Hardware.motors[3];
        }
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
        }// to turn right, make direction = 1 to turn left, make direction = -1,
        // to go forward make direction = 0 to go in reverse make direction = -2

    }
}

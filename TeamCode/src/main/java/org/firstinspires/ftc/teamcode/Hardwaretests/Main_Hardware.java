package org.firstinspires.ftc.teamcode.Hardwaretests;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;



public class Main_Hardware extends LinearOpMode_Handler {

    public enum RunMode {
        TeleOp,
        Autonomous
    }

    public enum DriveMode {
        POV,
        Tank,
        Arcade,
    }

    //Creates new RunMode and DriveMode Objects
    public static RunMode runMode = RunMode.TeleOp;
    public static DriveMode driveMode = DriveMode.POV;

    public static HardwareMap HMap;
    //public static Telemetry telemetry;

    //Compiles inside of the RunOpMode Autonomous
    protected void initAutonomous(HardwareMap nHMap, Telemetry nTelemetry) {
        runMode = RunMode.Autonomous;
        HMap = nHMap;
        //telemetry = nTelemetry;
        //driveMode = newDriveMode;
        //runMode = RunMode.TeleOp;
        //	Motors
        Motors_Hardware.initMotors(nHMap);
        //	Servos
        //Servo_Hardware.InitServos(runMode);
        //	Sensors
        Servo_Hardware.InitServos(nHMap);
    }

    //Compiles inside of the RunOpMode TeleOp
    protected void initTeleOp(HardwareMap nHMap, Telemetry nTelemetry){
        runMode = RunMode.TeleOp;
        HMap = nHMap;
        //telemetry = nTelemetry;
        //driveMode = newDriveMode;
        //runMode = RunMode.TeleOp;
        //	Motors
        Motors_Hardware.initMotors(nHMap);
        //	Servos
        //Servo_Hardware.InitServos(runMode);
        //	Sensors
        Servo_Hardware.InitServos(nHMap);
    }
}
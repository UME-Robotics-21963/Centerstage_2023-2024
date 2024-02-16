package org.firstinspires.ftc.teamcode.Hardwaretests;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class Motors_Hardware {
    /**
     *  {@code @Author} [Marcus Turley]
     *  todo"duck"  */


        //Creates a new array of DCMotor Objects
        public static DcMotorEx[] motors = new DcMotorEx[4];

        //Creates a new array of DCMotor Objects
        public static DcMotorEx[] intake = new DcMotorEx[2];


        //Creates a new array of DCMotor Objects


        //Initializes the motors
        public static void initMotors(HardwareMap HMap) {
            //Sets all the motors' names
            hardwareMap(motors, HMap, "rightFrontdrive", "leftFrontdrive", "rightBackdrive", "leftBackdrive");
            hardwareMap(intake, HMap,  "slide", "slide2");


            //Sets all the motors' directions to forwards
            setDirections(motors, DcMotorSimple.Direction.REVERSE, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.REVERSE,  DcMotorSimple.Direction.FORWARD);
            setDirections(intake, DcMotorSimple.Direction.FORWARD, DcMotorSimple.Direction.FORWARD);


            //Sets all the motors'
            brakeBehaviour(motors, DcMotor.ZeroPowerBehavior.BRAKE);
            brakeBehaviour(intake, DcMotor.ZeroPowerBehavior.BRAKE);

		/*
		//Checks the RunMode if RunMode is not TeleOp or Autonomous
		if(runMode == Robot_Hardware.RunMode.TeleOp) {
			//Sets all the motors to RunWithoutEncoder
			EncoderMode(Motors, DcMotor.RunMode.RUN_WITHOUT_ENCODER);
		} else if(runMode == Robot_Hardware.RunMode.Autonomous) {
			//Sets all the motors to RunUsingEncoder
			EncoderMode(Motors, DcMotor.RunMode.RUN_USING_ENCODER);
		} else {
			Prints unknown pointer exception
			Robot_Hardware.telemetry.addLine("Motor_Hardware:");
			Robot_Hardware.telemetry.addLine("RunMode is set to unknown variable.");
			Robot_Hardware.telemetry.update();
		}*/
        }


    //Sets all motors names on the phone
        public static void hardwareMap(DcMotor[] Motors, HardwareMap hardwareMap, String... names) {
            for(int i = 0; i < names.length; i++){
                Motors[i] = hardwareMap.get(DcMotor.class, names[i]);
            }
        }

        //Sets all motors to a ZeropowersBehavior
        public static void brakeBehaviour(DcMotor[] Motors, DcMotor.ZeroPowerBehavior... behaviours) {
            for(int i = 0; i < behaviours.length; i++){
                Motors[i].setZeroPowerBehavior(behaviours[i]);
            }
        }

        //Sets all motors encoders to a RunMode
        public static void EncoderMode(DcMotor[] Motors, DcMotor.RunMode... runMode) {
            for(int i = 0; i < runMode.length; i++){
                Motors[i].setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                Motors[i].setMode(runMode[i]);
            }
        }

        //Sets all motors direction to a direction
        public static void setDirections(DcMotor[] Motors, DcMotorSimple.Direction... dir) {
            for(int i = 0; i < dir.length; i++){
                Motors[i].setDirection(dir[i]);
            }
        }

        //Sets all motors powers based on their index
        public static void setPowers(DcMotor[] Motors, double... powers) {
            for(int i = 0; i < powers.length; i++){
                Motors[i].setPower(powers[i]);
            }
        }

        //Sets all motors powers based on the length of the array
        public static void setPowers(DcMotor[] Motors, int[] indexes, double... powers) {
            for(int i = 0; i < indexes.length; i++){
                Motors[indexes[i]].setPower(powers[i]);
            }
        }

        //Sets all motors powers based on their index
        public static void setTargetPosition(DcMotor[] Motors, int... powers) {
            for(int i = 0; i < powers.length; i++){
                Motors[i].setTargetPosition(powers[i]);
            }
        }
    }


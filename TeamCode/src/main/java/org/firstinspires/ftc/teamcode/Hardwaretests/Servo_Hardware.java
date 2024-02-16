package org.firstinspires.ftc.teamcode.Hardwaretests;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;

/**
 *  @Author [Marcus Turley]
 * */
public class Servo_Hardware {

    //Creates a new array of Servos Objects
    public static CRServo[] CRServos = new CRServo[2];
    public static ServoImplEx[] Servos = new ServoImplEx[0];
    //Initializes the Servos
    public static void InitServos(HardwareMap hMap) {
        //Sets all the Servos' names
        hardwareMap(CRServos, hMap, "lClaw", "plane");
        hardwareMap(Servos, hMap, "");
        setDirections(CRServos, CRServo.Direction.FORWARD, CRServo.Direction.FORWARD);
/*
		//Checks the RunMode if RunMode is not TeleOp or Autonomous
		if(runMode == Robot_Hardware.RunMode.TeleOp) {
			SetPositions(Servos,0,0,0,0);
		} else if(runMode == Robot_Hardware.RunMode.Autonomous) {
		} else {
			Prints unknown pointer exception
			Robot_Hardware.telemetry.addLine("Servo_Hardware:");
			Robot_Hardware.telemetry.addLine("RunMode is set to unknown variable.");
			Robot_Hardware.telemetry.update();
		}*/
    }

    //Sets all Servos names on the phone
    private static void hardwareMap(CRServo[] CRServos, HardwareMap hardwareMap, String... names) {
        for(int i = 0; i < names.length; i++){
            CRServos[i] = hardwareMap.get(CRServo.class, names[i]);
        }
    }
    //Sets all CRServos direction to a direction
    public static void setDirections(CRServo[] CRServos, CRServo.Direction... directions) {
        for(int i = 0; i < directions.length; i++){
            CRServos[i].setDirection(directions[i]);
        }
    }

    //Sets all CRServos power based on their index
    public static void setPowers(CRServo[] CRServos, double... powers) {
        for(int i = 0; i < powers.length; i++){
            CRServos[i].setPower(powers[i]);
        }
    }

    //Sets all CRServos power based on the length of the array
    public static void setPowers(CRServo[] CRServos, int[] indexes, double... powers) {
        for(int i = 0; i < indexes.length; i++){
            CRServos[indexes[i]].setPower(powers[i]);
        }
    }

    //CRServo end, Servo begins

    //sets all Servos names on the phone
    public static void hardwareMap(Servo[] servos, HardwareMap hardwareMap, String... names) {
        for(int i = 0; i < names.length; i++){
            servos[i] = hardwareMap.get(Servo.class, names[i]);
        }
    }

    //Sets all Servos positions based on their index
    public static void setPositions(Servo[] CRServos, double... positions) {
        for(int i = 0; i < positions.length; i++){
            if(CRServos.length != positions.length)
                positions[i] = 1;
            CRServos[i].setPosition(positions[i]);
        }
    }

    //Sets all Servos positions based on the length of the array
    public static void setPositions(Servo[] CRServos, int[] indexes, double... positions) {
        for(int i = 0; i < indexes.length; i++){
            CRServos[indexes[i]].setPosition(positions[i]);
        }
    }
}
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


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.app.Activity;
import android.view.View;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.CRServoImplEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.NormalizedColorSensor;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoImplEx;
import com.qualcomm.robotcore.hardware.SwitchableLight;
import com.qualcomm.hardware.bosch.BNO055IMU.Parameters;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
motors
 leftFrontdrive 0
 rightFrontdrive 1
 rightBackdrive 2
 leftBackdrive 3
servo
 servo slot 1
 plane slot 2
I2C
 I2C: 00 IMU
 I2C: 01 Sensor_Color

Expansion Hub
 Slide 0
 **/
public class HWmap {

    public DcMotorEx BLDrive, FRDrive, BRDrive, FLDrive, inTakeMotor, Arm2;
    double drive, turn, strafe, FLPower, BLPower, FRPower, BRpower;
    public CRServo servo, plane;
    public ServoImplEx AA;
    public NormalizedColorSensor colorsensor;
    public View relativeLayout;
    double phaseNumber = 0;
    public Telemetry[] Tele = new Telemetry[1];
    public boolean[][] toggleInputs = new boolean[2][2];
    //to increase the number of booleans just increase both numbers by 1 for toggles
    /**
     {(1,0),(1,1)
     {(0,0),(0,1) intake 0,0 is not active 0,1 is active
     aka x = which set its in and y = what value it holds
     0 being false/ not active
     1 being true/ is active
     **/
    public IMU imu;

    Parameters meters = new BNO055IMU.Parameters();
    public NormalizedRGBA colors;
    public final float[] hsvValues = new float[3];

    public void initialize2(HardwareMap ahwMap) {
        HardwareMap hwMap = ahwMap;
        colorsensor = hwMap.get(NormalizedColorSensor.class, "sensor_color");
        imu = hwMap.get(IMU.class,"IMU");

        int relativeLayoutId = hwMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hwMap.appContext.getPackageName());

        relativeLayout = ((Activity) hwMap.appContext).findViewById(relativeLayoutId);

        if (colorsensor instanceof SwitchableLight)
        {
            ((SwitchableLight)colorsensor).enableLight(true);
        }

        colors = colorsensor.getNormalizedColors();
    }
    public void Telementry() {
        telemetry.addData("ClawSetPower", servo.getPower());
        telemetry.addData("plane power: ", plane.getPower());
        telemetry.addData("intake value: ", toggleInputs[0][0]);
        telemetry.addData("outTake value: ", toggleInputs[0][1]);
        telemetry.addData("arm1: ", inTakeMotor);
        telemetry.addData("arm2: ", Arm2);
        telemetry.update();
    }
   public void initialize(HardwareMap ahwMap) {
        HardwareMap hwMap = ahwMap;
        // Initialize the hardware variables. Note that the strings used here as parameters
        // to 'get' must correspond to the names assigned during the robot configuration
        // step (using the FTC Robot Controller app on the phone).
        BLDrive = hwMap.get(DcMotorEx.class, "leftBackdrive");
        FLDrive = hwMap.get(DcMotorEx.class, "leftFrontdrive");
        FRDrive = hwMap.get(DcMotorEx.class, "rightFrontdrive");
        BRDrive = hwMap.get(DcMotorEx.class, "rightBackdrive");
       servo = hwMap.get(CRServo.class, "lClaw");
        inTakeMotor = hwMap.get(DcMotorEx.class, "slide");
        Arm2 = hwMap.get(DcMotorEx.class, "slide2");
        colorsensor = hwMap.get(NormalizedColorSensor.class, "sensor_color");
       plane = hwMap.get(CRServoImplEx.class, "plane");
        imu = hwMap.get(IMU.class,"IMU");

       int relativeLayoutId = hwMap.appContext.getResources().getIdentifier("RelativeLayout", "id", hwMap.appContext.getPackageName());

       relativeLayout = ((Activity) hwMap.appContext).findViewById(relativeLayoutId);

       if (colorsensor instanceof SwitchableLight)
       {
           ((SwitchableLight)colorsensor).enableLight(true);
       }



        colors = colorsensor.getNormalizedColors();
        BLDrive.setDirection(DcMotorEx.Direction.REVERSE);
        BRDrive.setDirection(DcMotorEx.Direction.FORWARD);
        FLDrive.setDirection(DcMotorEx.Direction.FORWARD);
        FLDrive.setDirection(DcMotorEx.Direction.REVERSE);
        inTakeMotor.setDirection(DcMotorEx.Direction.FORWARD);
       Arm2.setDirection(DcMotorEx.Direction.REVERSE);
        BLDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
       BRDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        FLDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
       FLDrive.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
        BLDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        BRDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
       FLDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        FLDrive.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        inTakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
       Arm2.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
   }
}



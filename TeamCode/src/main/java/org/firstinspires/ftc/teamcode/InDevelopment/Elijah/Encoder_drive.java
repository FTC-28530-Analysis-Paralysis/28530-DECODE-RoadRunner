package org.firstinspires.ftc.teamcode.InDevelopment.Elijah;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
@Autonomous
public class Encoder_drive extends LinearOpMode {


    // create motors
    public DcMotor rightRearDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftRearDrive = null;
    public DcMotor leftFrontDrive = null;

    @Override
    public void runOpMode() throws InterruptedException {
        // gets motor
        rightRearDrive = hardwareMap.get(DcMotor.class, "rightRearDrive");
        leftRearDrive = hardwareMap.get(DcMotor.class, "leftRearDrive");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");

        // set direction
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightRearDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftRearDrive.setDirection(DcMotor.Direction.REVERSE);

        // resets encoder
        leftRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightRearDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftRearDrive.setTargetPosition(5000);
        leftFrontDrive.setTargetPosition(5000);
        rightRearDrive.setTargetPosition(5000);
        rightFrontDrive.setTargetPosition(5000);


        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightRearDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();



    }
}

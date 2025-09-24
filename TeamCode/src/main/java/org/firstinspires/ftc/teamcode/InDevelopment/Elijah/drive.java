package org.firstinspires.ftc.teamcode.InDevelopment.Elijah;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;


@TeleOp(name="ELIJAH OPMODE??")
public class drive extends LinearOpMode
{
 public DcMotor RightBackDrive= null;
    public DcMotor RightFrontDrive = null;
    public DcMotor LeftBackDrive = null;
    public DcMotor LeftFrontDrive = null;





    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        RightBackDrive = hardwareMap.get(DcMotor.class, "RightBackDrive");
        LeftBackDrive = hardwareMap.get(DcMotor.class, "LeftBackDrive");
        LeftFrontDrive = hardwareMap.get(DcMotor.class, "LeftFrontDrive");
        RightFrontDrive = hardwareMap.get(DcMotor.class, "RightFrontDrive");

        RightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        RightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        LeftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        LeftBackDrive.setDirection(DcMotor.Direction.REVERSE);


        waitForStart();

        while (opModeIsActive()) {




















        }

        }









    }























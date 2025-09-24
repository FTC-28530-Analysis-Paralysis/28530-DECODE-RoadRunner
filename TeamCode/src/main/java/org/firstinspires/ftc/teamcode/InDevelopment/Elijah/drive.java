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

    public void setDrivePower(double leftFront, double leftRear, double rightFront, double rightRear) {
        // Output the values to the motor drives.
        LeftFrontDrive.setPower(leftFront);
        LeftBackDrive.setPower(leftRear);
        RightFrontDrive.setPower(rightFront);
        RightBackDrive.setPower(rightRear);
    }
    public void mecanumDrive(double forward, double strafe, double turn) {
        // Combine forward, strafe, and turn for blended motion.
        double LeftFrontDrive = forward + strafe + turn;
        double LeftBackDrive = forward - strafe + turn;
        double RightFrontDrive = forward - strafe - turn;
        double RightBackDrive = forward + strafe - turn;

        // Scale the values so none of them exceed +/- 1.0
        double max1 = Math.max(Math.abs(LeftFrontDrive),Math.abs(LeftBackDrive));
        double max2 = Math.max(Math.abs(RightFrontDrive), Math.abs(RightBackDrive));
        double max = Math.max(Math.abs(max1), Math.abs(max2));
        if (max > 1.0)
        {
            LeftFrontDrive /= max;
            LeftBackDrive /= max;
            RightFrontDrive /= max;
            RightBackDrive /= max;
        }

        // with the new scaled values, send power to the motors.
        setDrivePower(LeftFrontDrive, LeftBackDrive, RightFrontDrive, RightBackDrive);
    }



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























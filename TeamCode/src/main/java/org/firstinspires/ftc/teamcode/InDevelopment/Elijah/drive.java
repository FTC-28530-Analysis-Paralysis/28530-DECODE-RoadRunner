package org.firstinspires.ftc.teamcode.InDevelopment.Elijah;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name="ELIJAH OPMODE??")
public class drive extends LinearOpMode {
    public DcMotor RightBackDrive = null;
    public DcMotor RightFrontDrive = null;
    public DcMotor LeftBackDrive = null;
    public DcMotor LeftFrontDrive = null;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Hardware mapping
        RightBackDrive = hardwareMap.get(DcMotor.class, "RightBackDrive");
        LeftBackDrive = hardwareMap.get(DcMotor.class, "LeftBackDrive");
        LeftFrontDrive = hardwareMap.get(DcMotor.class, "LeftFrontDrive");
        RightFrontDrive = hardwareMap.get(DcMotor.class, "RightFrontDrive");

        // Set motor directions
        RightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        RightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        LeftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        LeftBackDrive.setDirection(DcMotor.Direction.REVERSE);

        LeftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LeftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the driver to press START
        waitForStart();

        // This loop runs continuously after START is pressed
        while (opModeIsActive()) {

            // Get joystick values
            double drive = -gamepad1.right_stick_y;  // Controls forward and backward movement
            double strafe = gamepad1.left_stick_x;   // Controls side-to-side movement
            double turn = gamepad1.right_stick_x;    // Controls turning/rotation

            // Combine the joystick inputs to calculate the power for each wheel
            double leftFrontPower = drive + strafe + turn;
            double rightFrontPower = drive - strafe - turn;
            double leftBackPower = drive - strafe + turn;
            double rightBackPower = drive + strafe - turn;

            // Normalize the wheel speeds to be between -1 and 1
            double max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower /= max;
                rightFrontPower /= max;
                leftBackPower /= max;
                rightBackPower /= max;
            }

            // Send the calculated power to the motors
            LeftFrontDrive.setPower(leftFrontPower);
            RightFrontDrive.setPower(rightFrontPower);
            LeftBackDrive.setPower(leftBackPower);
            RightBackDrive.setPower(rightBackPower);

            // Show the joystick and wheel power values on the Driver Hub screen
            telemetry.addData("Status", "Running");
            telemetry.addData("Drive (Fwd/Rev)", "%.2f", drive);
            telemetry.addData("Strafe (Side)", "%.2f", strafe);
            telemetry.addData("Turn (Rotate)", "%.2f", turn);
            telemetry.addData("Front left/Right Power", "%.2f, %.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right Power", "%.2f, %.2f", leftBackPower, rightBackPower);
            telemetry.update();

        }
    }
}

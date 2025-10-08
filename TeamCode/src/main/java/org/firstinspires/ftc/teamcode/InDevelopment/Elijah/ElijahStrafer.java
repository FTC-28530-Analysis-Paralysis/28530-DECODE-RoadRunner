package org.firstinspires.ftc.teamcode.InDevelopment.Elijah;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name="Elijah Strafer")
public class ElijahStrafer extends LinearOpMode {
    public DcMotor rightBackDrive = null;
    public DcMotor rightFrontDrive = null;
    public DcMotor leftBackDrive = null;
    public DcMotor leftFrontDrive = null;
    public DcMotor leftFlywheel = null;
    public DcMotor rightFlywheel = null;



    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Hardware mapping
        rightBackDrive = hardwareMap.get(DcMotor.class, "rightBackDrive");
        leftBackDrive = hardwareMap.get(DcMotor.class, "leftBackDrive");
        leftFrontDrive = hardwareMap.get(DcMotor.class, "leftFrontDrive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "rightFrontDrive");
        leftFlywheel = hardwareMap.get(DcMotor.class, "leftFlywheel");
        rightFlywheel = hardwareMap.get(DcMotorEx.class, "rightFlywheel");

        // Set motor directions
        rightFlywheel.setDirection(DcMotor.Direction.FORWARD);
        leftFlywheel.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);

        leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Wait for the driver to press START
        waitForStart();

        // This loop runs continuously after START is pressed
        while (opModeIsActive()) {

            // Get joystick values
            double drive = -gamepad1.left_stick_y;  // Controls forward and backward movement
            double strafe = gamepad1.left_stick_x;   // Controls side-to-side movement
            double turn = gamepad1.right_stick_x;    // Controls turning/rotation
            double  power = gamepad1.right_trigger;

            rightFlywheel.setPower(power);
            leftFlywheel.setPower(power);

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
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);







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

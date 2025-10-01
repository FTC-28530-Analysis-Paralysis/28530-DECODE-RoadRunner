package org.firstinspires.ftc.teamcode.InDevelopment.Evan_work;
// autonomous program that drives bot forward a set distance, stops then
// backs up to the starting point using encoders to measure the distance.
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

@Autonomous(name="Drive Encoder Evan", group="Exercises")
//@Disabled
public class Drive extends LinearOpMode
{
    DcMotor rightBackDrive;
    DcMotor rightFrontDrive;
    DcMotor leftBackDrive;
    DcMotor leftFrontDrive;

    @Override
    public void runOpMode() throws InterruptedException
    {
        rightBackDrive = hardwareMap.dcMotor.get("rightBackDrive");
        leftBackDrive = hardwareMap.dcMotor.get("leftBackDrive");
        leftFrontDrive = hardwareMap.dcMotor.get("leftFrontDrive");
        rightFrontDrive = hardwareMap.dcMotor.get("rightFrontDrive");

        // You will need to set this based on your robot's
        // gearing to get forward control input to result in
        // forward motion.
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);

        // reset encoder counts kept by motors.
        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // set motors to run forward for 5000 encoder counts.
        leftBackDrive.setTargetPosition(5000);
        leftFrontDrive.setTargetPosition(5000);
        rightBackDrive.setTargetPosition(5000);
        rightFrontDrive.setTargetPosition(5000);


        // set motors to run to target encoder position and stop with brakes on.
        leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        telemetry.addData("Mode", "waiting");
        telemetry.update();

        // wait for start button.

        waitForStart();

        telemetry.addData("Mode", "running");
        telemetry.update();

        // set both motors to 25% power. Movement will start. Sign of power is
        // ignored as sign of target encoder position controls direction when
        // running to position.

        leftFrontDrive.setPower(0.25);
        leftBackDrive.setPower(0.25);
        rightFrontDrive.setPower(0.25);
        rightBackDrive.setPower(0.25);

        // wait while opmode is active and left motor is busy running to position.

        while (opModeIsActive() && leftFrontDrive.isBusy() && leftBackDrive.isBusy())   //leftMotor.getCurrentPosition() < leftMotor.getTargetPosition())
        {
            telemetry.addData("encoder-fwd-left", leftFrontDrive.getCurrentPosition() + "  busy=" + leftFrontDrive.isBusy());
            telemetry.addData("encoder-bwd-right", leftBackDrive.getCurrentPosition() + "  busy=" + leftBackDrive.isBusy());
            telemetry.addData("encoder-fwd-right", rightFrontDrive.getCurrentPosition() + "  busy=" + rightFrontDrive.isBusy());
            telemetry.addData("encoder-bwd-left", rightBackDrive.getCurrentPosition() + "  busy=" + rightBackDrive.isBusy());
            telemetry.update();
            idle();
        }

        // set motor power to zero to turn off motors. The motors stop on their own but
        // power is still applied so we turn off the power.

        leftFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);

        // wait 5 sec to you can observe the final encoder position.
        resetRuntime();



        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-fwd-left-end", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-bwd-right-end", leftBackDrive.getCurrentPosition());
            telemetry.addData("encoder-fwd-right-end", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-bwd-left-end", rightBackDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // From current position back up to starting point. In this example instead of
        // having the motor monitor the encoder we will monitor the encoder ourselves.

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        leftFrontDrive.setTargetPosition(0);
        leftBackDrive.setTargetPosition(0);
        rightFrontDrive.setTargetPosition(0);
        rightBackDrive.setTargetPosition(0);

        // Power sign matters again as we are running without encoder.
        leftFrontDrive.setPower(-0.25);
        leftBackDrive.setPower(-0.25);
        rightBackDrive.setPower(-0.25);
        rightFrontDrive.setPower(-0.25);

        while (opModeIsActive() && leftFrontDrive.getCurrentPosition() > leftFrontDrive.getTargetPosition() && leftBackDrive.getCurrentPosition() > leftBackDrive.getTargetPosition())
        {
            telemetry.addData("encoder-back-left", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", leftBackDrive.getCurrentPosition());
            telemetry.addData("encoder-back-left", rightBackDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right", rightFrontDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }

        // set motor power to zero to stop motors.

        leftFrontDrive.setPower(0.0);
        leftBackDrive.setPower(0.0);
        rightFrontDrive.setPower(0.0);
        rightBackDrive.setPower(0.0);

        resetRuntime();



        while (opModeIsActive() && getRuntime() < 5)
        {
            telemetry.addData("encoder-front-left-end", leftFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right-end", leftBackDrive.getCurrentPosition());
            telemetry.addData("encoder-front-left-end", rightFrontDrive.getCurrentPosition());
            telemetry.addData("encoder-back-right-end", rightBackDrive.getCurrentPosition());
            telemetry.update();
            idle();
        }
    }
}

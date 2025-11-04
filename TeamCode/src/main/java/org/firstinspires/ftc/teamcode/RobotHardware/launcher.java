package org.firstinspires.ftc.teamcode.RobotHardware;

import static com.qualcomm.robotcore.hardware.DcMotor.ZeroPowerBehavior.BRAKE;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.DecodeRi3D;

public class launcher {

   private final double FEED_TIME_SECONDS = 0.80; //The feeder servos run this long when a shot is requested.
   private final double STOP_SPEED = 0.0; //We send this power to the servos when we want them to stop.
   private final double FULL_SPEED = 1.0;

   private final double LAUNCHER_CLOSE_TARGET_VELOCITY = 1200; //in ticks/second for the close goal.
   private final double LAUNCHER_CLOSE_MIN_VELOCITY = 1175; //minimum required to start a shot for close goal.

   private final double LAUNCHER_FAR_TARGET_VELOCITY = 1350; //Target velocity for far goal
   private final double LAUNCHER_FAR_MIN_VELOCITY = 1325; //minimum required to start a shot for far goal.

    private DcMotorEx leftLauncher = null;
    private DcMotorEx rightLauncher = null;
    private CRServo leftFeeder = null;
    private CRServo rightFeeder = null;

    ElapsedTime leftFeederTimer = new ElapsedTime();
    ElapsedTime rightFeederTimer = new ElapsedTime();

    private enum LaunchState {
        IDLE,
        SPIN_UP,
        LAUNCH,
        LAUNCHING,
    }
    private LaunchState launchState;

    public void init(HardwareMap hwMap) {

        leftLauncher = hwMap.get(DcMotorEx.class, "left_launcher");
        rightLauncher = hwMap.get(DcMotorEx.class, "right_launcher");
        leftFeeder = hwMap.get(CRServo.class, "left_feeder");
        rightFeeder = hwMap.get(CRServo.class, "right_feeder");

        leftLauncher.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFeeder.setDirection(DcMotorSimple.Direction.REVERSE);

        leftLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightLauncher.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftLauncher.setZeroPowerBehavior(BRAKE);
        rightLauncher.setZeroPowerBehavior(BRAKE);

        leftLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(300, 0, 0, 10));
        rightLauncher.setPIDFCoefficients(DcMotor.RunMode.RUN_USING_ENCODER, new PIDFCoefficients(300, 0, 0, 10));

        launchState = LaunchState.IDLE;
    }
    public void updateState(){
        switch(launchState) {
            case IDLE:
                break;
            case SPIN_UP:
                leftLauncher.setVelocity(LAUNCHER_CLOSE_TARGET_VELOCITY);
                rightLauncher.setVelocity(LAUNCHER_CLOSE_TARGET_VELOCITY);
                if (leftLauncher.getVelocity() >= LAUNCHER_CLOSE_MIN_VELOCITY)
                /// TODO Ask about how to make this line up ///
                /// (rightLauncher.getVelocity() >= LAUNCHER_CLOSE_MIN_VELOCITY)

                    launchState = LaunchState.LAUNCH;
                break;
             case LAUNCH:





        }
    }
}


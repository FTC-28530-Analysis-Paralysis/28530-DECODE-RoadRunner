package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RobotHardware.Intake;
import org.firstinspires.ftc.teamcode.RobotHardware.Launcher;
import org.firstinspires.ftc.teamcode.RobotHardware.StraferDrive;

@TeleOp(name = "Scrimmage Teleop", group = "01 Competition")
public class ScrimmageTeleOp extends OpMode {

    StraferDrive drive = new StraferDrive();
    Launcher launcher = new Launcher();
    Intake intake = new Intake();

    @Override
    public void init() {
        drive.init(hardwareMap);
        launcher.init(hardwareMap);
        intake.init(hardwareMap);
    }

    @Override
    public void loop() {
        launcher.updateState();

        drive.drive(-gamepad1.left_stick_y, gamepad1.left_stick_x, gamepad1.right_stick_x);
    }
}

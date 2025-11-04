package org.firstinspires.ftc.teamcode.RobotHardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.DecodeRi3D;

public class Intake {

    private DcMotor intake = null;

    private enum IntakeState {
        ON,
        OFF;
    }

    private IntakeState intakeState = IntakeState.OFF;

    public void init(HardwareMap hwMap) {
        intake = hwMap.get(DcMotor.class, "intake");

        intake.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    switch (intakeState){
        case ON:
            intakeState = IntakeState.OFF;
            intake.setPower(0);
            break;
        case OFF:
            intakeState = IntakeState.ON;
            intake.setPower(1);
            break;
    }

}

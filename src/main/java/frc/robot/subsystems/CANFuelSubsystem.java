package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import static frc.robot.Constants.FuelConstants.*;

public class CANFuelSubsystem extends SubsystemBase {

    private final TalonSRX feederRoller;
    private final TalonSRX intakeLauncherRoller;

    public CANFuelSubsystem() {
        feederRoller = new TalonSRX(FEEDER_MOTOR_ID);
        intakeLauncherRoller = new TalonSRX(INTAKE_LAUNCHER_MOTOR_ID);

        // Optional inversion
        intakeLauncherRoller.setInverted(true);

        // Optional: configure current limits
        feederRoller.configSupplyCurrentLimit(new com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration(true, FEEDER_MOTOR_CURRENT_LIMIT, FEEDER_MOTOR_CURRENT_LIMIT, 1.0));
        intakeLauncherRoller.configSupplyCurrentLimit(new com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration(true, LAUNCHER_MOTOR_CURRENT_LIMIT, LAUNCHER_MOTOR_CURRENT_LIMIT, 1.0));

        System.out.println("CANFuelSubsystem initialized (TalonSRX)");
    }

    public void setFeederRoller(double percentOutput) {
        feederRoller.set(ControlMode.PercentOutput, percentOutput / 12.0); // convert volts to percent
    }

    public void setIntakeLauncherRoller(double percentOutput) {
        intakeLauncherRoller.set(ControlMode.PercentOutput, percentOutput / 12.0);
    }

    public void stop() {
        feederRoller.set(ControlMode.PercentOutput, 0);
        intakeLauncherRoller.set(ControlMode.PercentOutput, 0);
    }
}

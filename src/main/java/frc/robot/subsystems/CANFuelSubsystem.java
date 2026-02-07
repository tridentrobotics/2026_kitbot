package frc.robot.subsystems;

import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import static frc.robot.Constants.FuelConstants.*;

public class CANFuelSubsystem extends SubsystemBase {
    private final SparkMax feederRoller;
    private final SparkMax intakeLauncherRoller;
    public CANFuelSubsystem() {
        intakeLauncherRoller = new SparkMax(INTAKE_LAUNCHER_MOTOR_ID, MotorType.kBrushed);
        feederRoller = new SparkMax(FEEDER_MOTOR_ID, MotorType.kBrushed);

        SparkMaxConfig feederConfig = new SparkMaxConfig();
        feederConfig.smartCurrentLimit(FEEDER_MOTOR_CURRENT_LIMIT);
        feederRoller.configure(feederConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);


        SparkMaxConfig launcherConfig = new SparkMaxConfig();
        launcherConfig.inverted(true);
        launcherConfig.smartCurrentLimit(LAUNCHER_MOTOR_CURRENT_LIMIT);

        intakeLauncherRoller.configure(launcherConfig, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        SmartDashboard.putNumber("Intaking Feeder Roller Value", INTAKING_FEEDER_VOLTAGE);
        SmartDashboard.putNumber("Intaking Intake Roller Value", INTAKING_INTAKE_VOLTAGE);
        SmartDashboard.putNumber("Launching Feeder Roller Value", LAUNCHING_FEEDER_VOLTAGE);
        SmartDashboard.putNumber("Launching Launcher Roller Value", LAUNCHING_LAUNCHER_VOLTAGE);
        SmartDashboard.putNumber("Spin-Up Feeder Roller Value", SPIN_UP_FEEDER_VOLTAGE);

    }

    public void setIntakeLauncherRoller(double voltage) {
        intakeLauncherRoller.setVoltage(voltage);
    }
    public void setFeederRoller(double voltage) {
        feederRoller.setVoltage(voltage);
    }
    public void stop() {
        feederRoller.set(0);
        intakeLauncherRoller.set(0);
    }
    @Override
    public void periodic() {
    }


}


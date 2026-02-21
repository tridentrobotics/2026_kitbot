package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;
import static frc.robot.Constants.OperatorConstants.*;
import edu.wpi.first.wpilibj.Joystick;
import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class Intake extends Command {
    CANFuelSubsystem fuelSubsystem;
    private final CommandXboxController operatorController;
    private final Joystick operatorJoystick;
    public Intake(CANFuelSubsystem fuelSystem, CommandXboxController controller, Joystick joystick) {
        
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
        this.operatorController = controller;
        this.operatorJoystick = joystick;
    }

    @Override
    public void initialize() {
        // No fixed voltage; speed is set in execute
    }
    private double lastIntakeVoltage = 0;
    private double lastFeederVoltage = 0;
    private double intakeVoltage = 0;
    private double feederVoltage = 0;

    @Override
    public void execute() {
        if (FLIGHTSTICK_ENABLED) {
        intakeVoltage =  INTAKING_INTAKE_VOLTAGE;
        feederVoltage =  INTAKING_FEEDER_VOLTAGE;

        } else{
            intakeVoltage = operatorController.getLeftTriggerAxis() * INTAKING_INTAKE_VOLTAGE;
            feederVoltage = operatorController.getLeftTriggerAxis() * INTAKING_FEEDER_VOLTAGE;
        }
            if (Math.abs(intakeVoltage - lastIntakeVoltage) > EPSILON || Math.abs(feederVoltage - lastFeederVoltage) > EPSILON) {
            
                double intakeRounded = new BigDecimal(intakeVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();
                double feederRounded = new BigDecimal(feederVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();

            
                System.out.println("Intake voltage: " + intakeRounded + ", Feeder voltage: " + feederRounded);
                lastFeederVoltage = feederVoltage;
                lastIntakeVoltage = intakeVoltage;
            }

            fuelSubsystem.setIntakeLauncherRoller(intakeVoltage);
            fuelSubsystem.setFeederRoller(feederVoltage);
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Intake command ended. Stopping motors.");
        fuelSubsystem.stop();
    }
}
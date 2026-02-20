package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;
import static frc.robot.Constants.OperatorConstants.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class Intake extends Command {
    CANFuelSubsystem fuelSubsystem;
    private final CommandXboxController operatorController;
    public Intake(CANFuelSubsystem fuelSystem, CommandXboxController controller) {
        
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
        this.operatorController = controller;
    }

    @Override
    public void initialize() {
        // No fixed voltage; speed is set in execute
    }
    private double lastIntakeVoltage = 0;
    private double lastFeederVoltage = 0;
    @Override
    public void execute() {
        if (FLIGHTSTICK_ENABLED) {
        double intakeVoltage =  INTAKING_INTAKE_VOLTAGE;
        double feederVoltage =  INTAKING_FEEDER_VOLTAGE;
        if (Math.abs(intakeVoltage - lastIntakeVoltage) > EPSILON || Math.abs(feederVoltage - lastFeederVoltage) > EPSILON) {
        
            double intakeRounded = new BigDecimal(intakeVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();
            double feederRounded = new BigDecimal(feederVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();

        
            System.out.println("Intake voltage: " + intakeRounded + ", Feeder voltage: " + feederRounded);
            lastFeederVoltage = feederVoltage;
            lastIntakeVoltage = intakeVoltage;
        }

        fuelSubsystem.setIntakeLauncherRoller(intakeVoltage);
        fuelSubsystem.setFeederRoller(feederVoltage);
        } else{
            double intakeVoltage = operatorController.getLeftTriggerAxis() * INTAKING_INTAKE_VOLTAGE;
            double feederVoltage = operatorController.getLeftTriggerAxis() * INTAKING_FEEDER_VOLTAGE;
            if (Math.abs(intakeVoltage - lastIntakeVoltage) > EPSILON || Math.abs(feederVoltage - lastFeederVoltage) > EPSILON) {
            
                double intakeRounded = new BigDecimal(intakeVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();
                double feederRounded = new BigDecimal(feederVoltage).setScale(4, RoundingMode.HALF_UP).doubleValue();

            
                System.out.println("Intake voltage: " + intakeRounded + ", Feeder voltage: " + feederRounded);
                lastFeederVoltage = feederVoltage;
                lastIntakeVoltage = intakeVoltage;
            }

            fuelSubsystem.setIntakeLauncherRoller(intakeVoltage*0.8);
            fuelSubsystem.setFeederRoller(feederVoltage*0.75);
        }
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
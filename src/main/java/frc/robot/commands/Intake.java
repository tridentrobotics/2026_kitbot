package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

public class Intake extends Command {
    CANFuelSubsystem fuelSubsystem;
    double triggerAxisPct;

    public Intake(CANFuelSubsystem fuelSystem, double triggerAxisPct) {
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
        this.triggerAxisPct = triggerAxisPct;
    }

    @Override
    public void initialize() {
        // No fixed voltage; speed is set in execute
    }

    @Override
    public void execute() {
        double intakeVoltage = triggerAxisPct * INTAKING_INTAKE_VOLTAGE;
        double feederVoltage = triggerAxisPct * INTAKING_FEEDER_VOLTAGE;

        System.out.println("Intake voltage: " + intakeVoltage + ", Feeder voltage: " + feederVoltage);

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
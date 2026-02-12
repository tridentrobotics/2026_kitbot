package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import static frc.robot.Constants.OperatorConstants.*;

public class Intake extends Command {
    private final CommandXboxController driverController = new CommandXboxController(DRIVER_CONTROLLER_PORT);
    CANFuelSubsystem fuelSubsystem;

    public Intake(CANFuelSubsystem fuelSystem) {
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
    }

    @Override
    public void initialize() {
        fuelSubsystem
                .setIntakeLauncherRoller(INTAKING_INTAKE_VOLTAGE*(driverController.getLeftTriggerAxis()));
        fuelSubsystem
                .setFeederRoller(INTAKING_FEEDER_VOLTAGE*(driverController.getLeftTriggerAxis()));
    }

    @Override
    public void execute() {
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        fuelSubsystem.stop();
    }
}
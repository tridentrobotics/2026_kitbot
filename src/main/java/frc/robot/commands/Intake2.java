package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;
import static frc.robot.Constants.OperatorConstants.OPERATOR_CONTROLLER_PORT;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;


public class Intake2 extends Command {
    CANFuelSubsystem fuelSubsystem;
    public final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
    public Intake2(CANFuelSubsystem fuelSystem) {
        
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
    }

    @Override
    public void initialize() {
        // No fixed voltage; speed is set in execute
    }
    
    @Override
    public void execute() {
        double intakeVoltage = operatorController.getLeftTriggerAxis() * INTAKING_INTAKE_VOLTAGE;
        double feederVoltage = operatorController.getLeftTriggerAxis() * INTAKING_FEEDER_VOLTAGE;

        System.out.println("Intake voltage: " + intakeVoltage + ", Feeder voltage: " + feederVoltage);

        fuelSubsystem.setIntakeLauncherRoller(intakeVoltage*.5);
        fuelSubsystem.setFeederRoller(feederVoltage*.5);
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
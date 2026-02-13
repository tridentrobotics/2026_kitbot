package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;
import static frc.robot.Constants.OperatorConstants.OPERATOR_CONTROLLER_PORT;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class Eject extends Command {
    CANFuelSubsystem fuelSubsystem;
    public final CommandXboxController operatorController = new CommandXboxController(OPERATOR_CONTROLLER_PORT);
    public Eject(CANFuelSubsystem fuelSystem){
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
    
    }
    @Override
    public void initialize(){
        
    }
    @Override
    public void execute(){
        double intakeVoltage = operatorController.getLeftTriggerAxis() * LAUNCHING_LAUNCHER_VOLTAGE;
        double feederVoltage = operatorController.getLeftTriggerAxis() * LAUNCHING_FEEDER_VOLTAGE;

        System.out.println("Intake voltage: " + intakeVoltage + ", Feeder voltage: " + feederVoltage);

        fuelSubsystem.setIntakeLauncherRoller(intakeVoltage);
        fuelSubsystem.setFeederRoller(feederVoltage);
    }
    @Override
    public void end(boolean interrupted){
        System.out.println("Intake command ended. Stopping motors.");
        fuelSubsystem.stop();
        
    }
    @Override
    public boolean isFinished(){
        return false; 
    }

}
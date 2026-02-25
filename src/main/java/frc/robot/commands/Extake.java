package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

public class Extake extends Command {
    CANFuelSubsystem fuelSubsystem;
    public Extake(CANFuelSubsystem fuelSystem){
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
    
    }
    @Override
    public void initialize(){
    System.out.println("Intake voltage: " + -LAUNCHING_LAUNCHER_VOLTAGE + ", Feeder voltage: " + LAUNCHING_FEEDER_VOLTAGE);
    }
    @Override
    public void execute(){
        double intakeVoltage = 1 * -LAUNCHING_LAUNCHER_VOLTAGE;
        double feederVoltage = 1 * LAUNCHING_FEEDER_VOLTAGE;

        

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
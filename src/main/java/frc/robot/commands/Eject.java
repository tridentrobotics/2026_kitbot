package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

public class Eject extends Command {
    CANFuelSubsystem fuelSubsystem;
    public Eject(CANFuelSubsystem fuelSystem){
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
    
    }
    @Override
    public void initialize(){
<<<<<<< HEAD
        fuelSubsystem.setIntakeLauncherRoller(-1*SmartDashboard.getNumber("Intaking intake roller value", INTAKING_INTAKE_VOLTAGE));
        fuelSubsystem.setFeederRoller(1*SmartDashboard.getNumber("Intaking feeder roller value", INTAKING_FEEDER_VOLTAGE));
=======
        fuelSubsystem.setIntakeLauncherRoller(1*SmartDashboard.getNumber("Intaking intake roller value", INTAKING_INTAKE_VOLTAGE));
        fuelSubsystem.setFeederRoller(1*SmartDashboard.getNumber("Intaking Feeder roller value", INTAKING_FEEDER_VOLTAGE));
>>>>>>> a1f35988c1527cda6188788effb843bed5507ba6
        
    }
    @Override
    public void execute(){
    }
    @Override
    public void end(boolean interrupted){
        fuelSubsystem.setIntakeLauncherRoller(0);
        fuelSubsystem.setFeederRoller(0);
        
    }
    @Override
    public boolean isFinished(){
        return false; 
    }

}
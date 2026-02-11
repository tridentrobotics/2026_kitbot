package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.CANFuelSubsystem;
import static frc.robot.Constants.FuelConstants.*;

public class Launch extends Command {

    CANFuelSubsystem fuelSubsystem;

    public Launch(CANFuelSubsystem fuelSystem){
        addRequirements(fuelSystem);
        this.fuelSubsystem = fuelSystem;
   }
   
   @Override
   public void initialize(){
    fuelSubsystem.setIntakeLauncherRoller(1*SmartDashboard.getNumber("Launching launcher roller value", LAUNCHING_LAUNCHER_VOLTAGE));
   fuelSubsystem.setFeederRoller(1*SmartDashboard.getNumber("Launching feeder roller value", LAUNCHING_FEEDER_VOLTAGE));
}

@Override
public void execute(){
}

@Override
public void end(boolean interrupted){
    fuelSubsystem.stop();
}

@Override 
public boolean isFinished(){
    return false; 
}
}

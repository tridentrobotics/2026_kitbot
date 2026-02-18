package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.FuelConstants;
import frc.robot.subsystems.CANFuelSubsystem;


public class IntakeSequence extends SequentialCommandGroup {
public IntakeSequence(CANFuelSubsystem fuelSubsystem){
    
    addCommands(
        new Intake(fuelSubsystem).withTimeout(2),
        new Intake2(fuelSubsystem));
        
}
}
